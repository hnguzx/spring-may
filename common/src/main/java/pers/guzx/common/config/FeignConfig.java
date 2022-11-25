//package pers.guzx.common.config;
//
//import feign.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.support.BasicAuthenticationInterceptor;
//
//@Configuration
//public class FeignConfig {
//
//    @Bean
//    Logger.Level feignLogLevel(){
//        return Logger.Level.FULL;
//    }
//
//    /**
//     * 被调用方有安全验证时需要放开
//     */
//    @Bean
//    BasicAuthenticationInterceptor basicAuthenticationInterceptor(){
//        return new BasicAuthenticationInterceptor("username", "password");
//    }
//}
