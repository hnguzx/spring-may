package pers.guzx.user.authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 25446
 */
@Slf4j
@Component
public class ObjectPostProcessorImpl implements ObjectPostProcessor {
    @Override
    public Object postProcess(Object object) {
        return null;
    }
}
