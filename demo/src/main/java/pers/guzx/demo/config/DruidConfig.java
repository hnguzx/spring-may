package pers.guzx.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid连接池基本配置
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(druid());
    }

    /**
     * 数据库防火墙配置
     * @return
     */
    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();

        WallConfig config = new WallConfig();
        // 不允许select *
        config.setSelectAllColumnAllow(false);
        // 一次性执行多个语句
        config.setMultiStatementAllow(true);
        // 只允许执行基本语句
        config.setNoneBaseStatementAllow(false);
        // 严格检查语法
        config.setStrictSyntaxCheck(true);

        // 表结构是否允许修改
        config.setTruncateAllow(false);
        config.setCreateTableAllow(false);
        config.setAlterTableAllow(false);
        config.setDropTableAllow(false);
        // 不允许切库与查看数据库表结构
        config.setUseAllow(false);
        config.setDescribeAllow(false);
        config.setShowAllow(false);

        wallFilter.setConfig(config);
        return wallFilter;
    }

    /**
     * 监控控制台配置
     * @return
     */
    @Bean
    @Profile("dev")
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        // 控制台登录用户
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");

        //默认就是允许所有访问
        initParams.put("allow", "127.0.0.1");
        // deny比allow优先级更高
        initParams.put("deny", "");
        // 禁止页面重置
        initParams.put("resetEnable", "true");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * web-jdbc的监控配置
     * "profileEnable";
     * "sessionStatEnable";
     * "sessionStatMaxCount";
     * "exclusions";
     * "principalSessionName";
     * "principalCookieName";
     * "realIpHeader";
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        // 排除请求
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        initParams.put("profileEnable","true");
        initParams.put("sessionStatEnable","true");
        initParams.put("sessionStatMaxCount","1000");
        bean.setInitParameters(initParams);
        // 拦截所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
