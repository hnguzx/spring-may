package pers.guzx.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 25446
 */
@Configuration
@ConditionalOnProperty(prefix = "spring", name = "redis")
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 集群配置
     * @param properties
     * @return
     */
    /*@Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public LettuceConnectionFactory redisConnectionFactory(RedisProperties properties) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(getClusterConfiguration(properties), getClientConfiguration(properties));
        // 使用前先校验连接
        factory.setValidateConnection(true);
        return factory;
    }*/

    /*public RedisClusterConfiguration getClusterConfiguration(RedisProperties properties) {
        if (properties.getCluster() == null) {
            return null;
        }

        RedisProperties.Cluster clusterProperties = properties.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());

        if (clusterProperties.getMaxRedirects() != null) {
            config.setMaxRedirects(clusterProperties.getMaxRedirects());
        }
        if (properties.getPassword() != null) {
            config.setPassword(RedisPassword.of(properties.getPassword()));
        }
        return config;
    }*/

    /*public LettuceClientConfiguration getClientConfiguration(RedisProperties properties) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        RedisProperties.Pool pool = properties.getLettuce().getPool();

        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);

        // Support adaptive cluster topology refresh and static refresh source
        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(ClusterTopologyRefreshOptions.builder()
                        .enablePeriodicRefresh()
                        .refreshPeriod(DEFAULT_REFRESH_PERIOD)
                        .enableAllAdaptiveRefreshTriggers()
                        .build())
                .socketOptions(SocketOptions.builder()
                        .connectTimeout(properties.getTimeout() == null? DEFAULT_CONNECT_TIMEOUT: properties.getTimeout()) // fail-fast
                        .tcpNoDelay(true)
                        .keepAlive(true).build())
                .build();

        // From the priority, read and write separation, read from the possible inconsistency, the final consistency CP
        return LettucePoolingClientConfiguration.builder()
                .commandTimeout(properties.getTimeout() == null ? DEFAULT_TIMEOUT : properties.getTimeout())
                .poolConfig(config)
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .clientOptions(clusterClientOptions)
                .build();
    }*/
}
