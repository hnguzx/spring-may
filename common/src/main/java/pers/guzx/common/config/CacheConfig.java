package pers.guzx.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

/**
 * @author 25446
 */
@Slf4j
@Configuration
public class CacheConfig implements CachingConfigurer {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public CacheManager cacheManager() {
        RedisSerializer<Object> json = RedisSerializer.json();
        redisTemplate.setValueSerializer(json);
        redisTemplate.setHashValueSerializer(json);
        RedisSerializationContext.SerializationPair<String> keySerializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer());
        RedisSerializationContext.SerializationPair<?> valueSerializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getHashValueSerializer());

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(keySerializationPair)
                .serializeValuesWith(valueSerializationPair)
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(3));

        RedisCacheManager cacheManager = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisTemplate.getConnectionFactory())
                .cacheDefaults(cacheConfiguration)
                .transactionAware()
                .build();

        return cacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {

        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                String cacheKey = method.getName() + Arrays.asList(params);
                log.info("cachKey:{}", cacheKey);
                return cacheKey;
            }
        };
    }
}
