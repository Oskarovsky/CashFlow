package com.oslyk.financeservice.repo

import com.oslyk.financeservice.gateway.FinanceController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import kotlin.math.log

@Repository
class DealRedisRepository {

    val logger: Logger = LoggerFactory.getLogger(DealRedisRepository::class.java)


    @Autowired
    lateinit var hashOperations: HashOperations<String, String, String>

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, Any>

    fun testHashOp() {
        logger.info("=== START TESTING ===")
        this.hashOperations = redisTemplate.opsForHash()
        logger.info("Result: {}", this.hashOperations)

        logger.info("=====")
        hashOperations.put("USER", "cashFlowKey", "cashFlowValue");

        logger.info("Result: {}", hashOperations.get("USER", "cashFlowKey"))
        logger.info("=== END TESTING ===")
    }
}