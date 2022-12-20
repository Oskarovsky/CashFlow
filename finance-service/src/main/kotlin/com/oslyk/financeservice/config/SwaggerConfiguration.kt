package com.oslyk.financeservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class SwaggerConfiguration {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory?): RedisTemplate<String, String>? {
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(redisConnectionFactory!!)
        return template
    }
}