package com.oslyk.cashflow.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDateTime
import java.util.Date

@RedisHash
data class Deal(
        @Indexed var name: String,
        val type: String?,
        val date: LocalDateTime?,
        var price: Double?
) {
    @get:Id
    var id: String? = null
    @Indexed @get:Reference var items: List<Item> = listOf()
}
