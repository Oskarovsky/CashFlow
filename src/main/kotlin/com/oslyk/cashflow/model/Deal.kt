package com.oslyk.cashflow.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
data class Deal(@Id val id: Int,
                var name: String,
                var price: Double)
