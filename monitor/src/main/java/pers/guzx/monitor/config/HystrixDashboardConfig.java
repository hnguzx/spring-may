//package pers.guzx.monitor.config;
//
//import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 服务提供端添加
// */
//@Configuration
//public class HystrixDashboardConfig {
//
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/manage/hystrix.stream");
//        registrationBean.setName("hystrix.stream");
//        return registrationBean;
//    }
//}
