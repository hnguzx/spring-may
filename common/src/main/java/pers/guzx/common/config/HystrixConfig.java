package pers.guzx.common.config;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 *
 * reference https://www.jianshu.com/p/2b070568ff89
 * @author 李鹏翔(Percy Lee)
 * @date 2019-10-30
 **/
@Configuration
public class HystrixConfig {

    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    @PostConstruct
    public void init() {
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerCommandExecutionHook(new HystrixHook());
    }


    /**
     *
     * 这个方案有点缺陷，参考：https://medium.com/@saurav24081996/java-hystrix-and-threadlocals-95ea9e194e83
     */
    public static class MdcHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

        @Override
        public <T> Callable<T> wrapCallable(Callable<T> callable) {
            return new MdcAwareCallable(callable, MDC.getCopyOfContextMap());
        }

        private class MdcAwareCallable<T> implements Callable<T> {

            private final Callable<T> delegate;

            private final Map<String, String> contextMap;

            public MdcAwareCallable(Callable<T> callable, Map<String, String> contextMap) {
                this.delegate = callable;
                this.contextMap = contextMap != null ? contextMap : new HashMap();
            }

            @Override
            public T call() throws Exception {
                try {
                    MDC.setContextMap(contextMap);
                    return delegate.call();
                } finally {
                    MDC.clear();
                }
            }
        }
    }

    public class HystrixHook extends HystrixCommandExecutionHook {

        private Map<String, String> mdcContextMap;

        @Override
        public <T> void onStart(HystrixInvokable<T> commandInstance) {
            mdcContextMap = MDC.getCopyOfContextMap();
        }

        @Override
        public <T> void onExecutionStart(HystrixInvokable<T> commandInstance) {
            setThreadLocals();
        }

        @Override
        public <T> void onFallbackStart(HystrixInvokable<T> commandInstance) {
            setThreadLocals();
        }

        @Override
        public <T> void onExecutionSuccess(HystrixInvokable<T> commandInstance) {
            this.clearMdcContext();
            super.onExecutionSuccess(commandInstance);
        }

        @Override
        public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
            this.clearMdcContext();
            return super.onExecutionError(commandInstance, e);
        }

        @Override
        public <T> Exception onFallbackError(HystrixInvokable<T> commandInstance, Exception e) {
            this.clearMdcContext();
            return super.onFallbackError(commandInstance, e);
        }

        @Override
        public <T> void onFallbackSuccess(HystrixInvokable<T> commandInstance) {
            this.clearMdcContext();
            super.onFallbackSuccess(commandInstance);
        }

        private void setThreadLocals() {
            MDC.setContextMap(mdcContextMap);
        }
        
        private void clearMdcContext() {
            MDC.clear();
        }
    }


}
