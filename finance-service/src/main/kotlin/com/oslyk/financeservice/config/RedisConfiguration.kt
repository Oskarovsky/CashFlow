package com.oslyk.financeservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

@Configuration
class RedisConfiguration {

    @Value("\${spring.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    lateinit var redisPort: String

    @Value("\${spring.redis.topic}")
    lateinit var redisTopic: String

    @Bean
    fun redisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        val jedisClientConfiguration = JedisClientConfiguration
                .builder()
                .usePooling()
                .build()
        val factory = JedisConnectionFactory(config, jedisClientConfiguration)
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(redisConnectionFactory())
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }

    @Bean
    fun hashOperations(): HashOperations<String, String, String> = redisTemplate().opsForHash()
}