package pers.guzx.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/17 16:20
 * @describe 参数校验配置
 */
@Configuration
public class ValidatorConfig {

    /**
     * 设置为快速失败模式，默认校验全部参数
     *
     * @return
     */
    /*@Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }*/
}
