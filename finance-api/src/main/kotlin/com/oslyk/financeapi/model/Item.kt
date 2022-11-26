package com.oslyk.financeapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("Item")
data class Item(
        @Indexed val name: String,
        val price: Double
) {
    @get:Id
    var id: String? = null
}
