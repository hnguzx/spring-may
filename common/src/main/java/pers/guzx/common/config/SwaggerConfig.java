//package pers.guzx.common.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author Guzx
// * @version 1.0
// * @date 2021/5/15 17:47
// * @describe
// */
//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * api的基本信息
//     * @return
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Swagger")
//                .description("博学之，审问之，慎思之，明辨之，笃行之")
//                .termsOfServiceUrl("#")
//                .contact(new Contact("guzx-个人博客", "#", "hnguzx@gmail.com"))
//                .version("1.0")
//                .build();
//    }
//}
