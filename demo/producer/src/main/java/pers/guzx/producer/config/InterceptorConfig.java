//package pers.guzx.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import pers.guzx.demo.interceptor.ParameterInterceptor;
//
///**
// * @author 25446
// * interceptor 相关配置
// */
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(parameterInterceptor()).addPathPatterns("/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//
//    @Bean
//    public ParameterInterceptor parameterInterceptor(){
//        return new ParameterInterceptor();
//    }
//}
