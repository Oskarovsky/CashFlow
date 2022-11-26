package com.oslyk.financeapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.time.LocalDate

@RedisHash
data class Deal(
        @Indexed var name: String,
        val type: String?,
        val date: LocalDate,
        var price: Double
) {
    @get:Id
    var id: String? = null
    @Indexed @get:Reference var items: List<Item> = listOf()
}
