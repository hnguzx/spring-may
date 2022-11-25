//package pers.guzx.common.config;
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.ZoneAvoidanceRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 使用时需要在启动类添加@RibbonClient注解
// */
//@Configuration
//public class RibbonConfig {
//
//    @Bean
//    public IRule iRule(){
//        return new ZoneAvoidanceRule();
//    }
//}
