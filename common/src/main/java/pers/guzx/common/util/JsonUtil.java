//package pers.guzx.common.util;
//
//import com.google.gson.*;
//import com.google.gson.internal.bind.DateTypeAdapter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StringUtils;
//import pers.guzx.common.enums.Language;
//
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author guzx
// * @version 1.0
// * @date 2022/6/14 16:31
// * @describe
// */
//@Slf4j
//public class JsonUtil {
//
//    /**
//     * json序列化默认配置
//     */
//    private static final GsonBuilder GSON_BUILDER = newDefaultGsonBuilder();
//
//    /**
//     * 调用该接口输出的json字符串不可逆，适用于给客户端返回数据
//     * 示例：
//     * {"resourceId":"test","resource":{"zh_HK":"測試用例","en_US":"test case"}} 根据语言不同会转换为
//     * {"resourceId":"test","resource":"測試用例"} 或 {"resourceId":"test","resource":"test case"}
//     *
//     * @param object
//     * @param language
//     * @return
//     */
//    public static String toJSONString(Object object, String language) {
//        return toJSONString(object, language, true);
//    }
//
//    public static String toJSONString(Object object, String language, boolean useDefault) {
//        return toJSONString(object, Language.fromString(language), useDefault);
//    }
//
//    public static String toJSONString(Object object, Language language) {
//        return toJSONString(object, language, true);
//    }
//
//    public static String toJSONString(Object object, Language language, boolean useDefault) {
//        return newDefaultGsonBuilder().registerTypeAdapter(LanguageText.class, new TypeAdapter<LanguageText>() {
//                    @Override
//                    public void write(JsonWriter out, LanguageText value) throws IOException {
//                        if (value == null) {
//                            out.nullValue();
//                            return;
//                        }
//                        out.value(I18nUtil.getDesignatedLanguageText(value, language, useDefault));
//                    }
//
//                    @Override
//                    public LanguageText read(JsonReader in) throws IOException {
//                        throw new UnsupportedOperationException("Not support deserialize here");
//                    }
//                })
//                .create()
//                .toJson(object);
//    }
//
//    /**
//     * object to json
//     * @param object
//     * @return
//     */
//    public static String toJSONString(Object object) {
//        return GSON_BUILDER.create().toJson(object);
//    }
//
//    /**
//     * json to object
//     * @param json
//     * @param tClass
//     * @param <T>
//     * @return
//     */
//    public static <T> T fromJSONString(String json, Class<T> tClass) {
//        return GSON_BUILDER.create().fromJson(json, tClass);
//    }
//
//    /**
//     * JsonElement转JavaBean
//     */
//    public static <T> T fromJson(JsonElement jsonElement, Class<T> clazz) {
//        return GSON_BUILDER.create().fromJson(jsonElement, clazz);
//    }
//
//    /**
//     * json to list with type
//     * @param json
//     * @param tClass
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> fromJSONString2List(String json, Class<T> tClass) {
//        if (!StringUtils.hasLength(json)) {
//            return new ArrayList<>();
//        }
//        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
//        List<T> res = new ArrayList<>();
//        array.forEach(item -> {
//            res.add(newDefaultGsonBuilder().create().fromJson(item, tClass));
//        });
//        return res;
//    }
//
//    /**
//     *
//     * @param json
//     * @return
//     */
//    public static Map toMap(String json) {
//        return (Map) GSON_BUILDER.create().fromJson(json, Object.class);
//    }
//
//    /**
//     *
//     * @param json
//     * @return
//     */
//    public static List toList(String json) {
//        return (List) GSON_BUILDER.create().fromJson(json, Object.class);
//    }
//
//    /**
//     * Json字符串转JsonElement
//     */
//    public static JsonElement parse(String jsonString) {
//        return new JsonParser().parse(jsonString);
//    }
//
//    /**
//     * JavaBean转JsonElement
//     */
//    public static JsonElement parse(Object object) {
//        return JsonUtil.newDefaultGsonBuilder().create().toJsonTree(object);
//    }
//
//    /**
//     * 获取属性
//     */
//    public static String getAsString(JsonObject jsonObject, String key) {
//        JsonElement jsonElement = jsonObject.get(key);
//        if (isNotNull(jsonElement)) {
//            return jsonElement.getAsString();
//        }
//        return null;
//    }
//
//    public static int getAsInt(JsonObject jsonObject, String key) {
//        JsonElement jsonElement = jsonObject.get(key);
//        if (isNotNull(jsonElement)) {
//            return jsonElement.getAsInt();
//        }
//        return 0;
//    }
//
//    public static long getAsLong(JsonObject jsonObject, String key) {
//        JsonElement jsonElement = jsonObject.get(key);
//        if (isNotNull(jsonElement)) {
//            return jsonElement.getAsLong();
//        }
//        return 0;
//    }
//
//    public static boolean getAsBoolean(JsonObject jsonObject, String key) {
//        JsonElement jsonElement = jsonObject.get(key);
//        if (isNotNull(jsonElement)) {
//            return jsonElement.getAsBoolean();
//        }
//        return false;
//    }
//
//    /**
//     * 是否包含key
//     */
//    public static boolean hasKey(JsonObject jsonObject, String key) {
//        JsonElement jsonElement = jsonObject.get(key);
//        return isNotNull(jsonElement);
//    }
//
//    public static boolean isNotNull(JsonElement jsonElement) {
//        return jsonElement != null && !jsonElement.isJsonNull();
//    }
//
//    /**
//     *
//     * @return
//     */
//    public static GsonBuilder newDefaultGsonBuilder() {
//
//        GenericGsonRedisSerializer.registerAdapterClass(ComponentData.class);
//        GenericGsonRedisSerializer.registerAdapterClass(Date.class);
//        GenericGsonRedisSerializer.registerAdapterClass(java.sql.Date.class);
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory())
//                .registerTypeAdapter(Date.class, new DateTypeAdapter())
//                .registerTypeAdapter(java.sql.Date.class, new SqlDateTypeAdapter())
//                .registerTypeAdapter(ComponentData.class, new ComponentDataTypeAdapter(gsonBuilder))
//                .excludeFieldsWithModifiers(Modifier.STATIC)
//                .serializeNulls();
//        return gsonBuilder;
//    }
//
//}
