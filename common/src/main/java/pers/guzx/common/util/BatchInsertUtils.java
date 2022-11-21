package pers.guzx.common.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static pers.guzx.common.util.GetBeanUtils.getSpringEntry;

@Slf4j
public class BatchInsertUtils {

    private static Map<Class, Map<String, String>> entity2DB = new ConcurrentHashMap<>() {
    };

    public static Boolean batchInsert(List collect, String tableName, Class entityClass) {
        getDBAllFields(tableName, entityClass);
        if (!CollectionUtils.isEmpty(collect)) {
            SqlSessionFactory sqlSessionFactory = getSpringEntry("sqlSessionFactory", SqlSessionFactory.class);
            List<String> parameters = new ArrayList<>();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(tableName);
            sql.append("(");
            // sql:insert into tableName(
            List<String> fields = Arrays.stream(entityClass.getDeclaredFields())
                    .map(Field::getName)
                    .collect(Collectors.toList());
            for (String field : fields) {
                if ("serialVersionUID".equals(field) || "id".equals(field)) {
                    continue;
                }
                sql.append(entity2DB.get(entityClass).get(field.toLowerCase())).append(",");
            }
            // sql:insert into tableName(field1,field2,
            sql.replace(sql.length() - 1, sql.length(), ") values(");
            // sql:insert into tableName(field1,field2) values(
            log.info("insert sql:{}", sql);
            int insertNumber;
            try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, false)) {
                collect.forEach(item -> {
                    for (String field : fields) {
                        PropertyDescriptor pd;
                        try {
                            if ("serialVersionUID".equals(field) || "id".equals(field)) {
                                continue;
                            }
                            pd = new PropertyDescriptor(field, entityClass);
                            Method getMethod = pd.getReadMethod();
                            sql.append("?,");
                            parameters.add(handlerField(getMethod.invoke(item)));
                        } catch (IntrospectionException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // sql:insert into tableName(field1,field2) values(?,?,
                    sql.replace(sql.length() - 1, sql.length(), "),(");
                });
                // sql:insert into tableName(field1,field2） values(?,?),(
                sql.replace(sql.length() - 2, sql.length(), ";");
                // sql:insert into tableName(field1,field2) values(?,?);
                try (PreparedStatement preparedStatement = sqlSession.getConnection().prepareStatement(sql.toString())) {
                    for (int i = 0; i < parameters.size(); i++) {
                        preparedStatement.setString(i + 1, parameters.get(i));
                    }
                    insertNumber = preparedStatement.executeUpdate();
                    sqlSession.commit();
                    log.info("{} batch insert success number:{}", tableName, insertNumber);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return insertNumber == collect.size();
        }
        return false;
    }

    /**
     * 获取指定entity对应表中的所有字段名
     * @param tableName
     * @param entityClass
     */
    public static void getDBAllFields(String tableName, Class entityClass) {
        if (!Strings.isNullOrEmpty(tableName)) {
            // 已存在
            if(entity2DB.containsKey(entityClass)){
                return;
            }
            entity2DB.put(entityClass, new HashMap<>());
            String sql = "select * from " + tableName;
            SqlSessionFactory sqlSessionFactory = getSpringEntry("sqlSessionFactory", SqlSessionFactory.class);

            try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, false);
                 PreparedStatement preparedStatement = sqlSession.getConnection().prepareStatement(sql)) {
                ResultSetMetaData metaData = preparedStatement.getMetaData();
                String columnName;
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    columnName = metaData.getColumnName(i + 1);
                    entity2DB.get(entityClass).put(columnName.replaceAll("_",""), columnName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            log.info("entityDB:{}", entity2DB);
        }
    }

    /**
     * entity2DB
     *
     * @param field
     * @return
     */
    public static String handlerField(Object field) {
        if (field != null) {
            if (field instanceof Date) {
                return DateUtils.formatDate((Date) field, "yyyy-MM-dd hh:mm:ss");
            } else if (field instanceof Boolean || field.getClass() == Boolean.class) {
                return field.equals(true) ? "1" : "0";
            } else {
                return String.valueOf(field);
            }
        }
        return null;
    }
}
