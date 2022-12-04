package com.oslyk.financeservice.repo

import com.oslyk.financeapi.model.Deal
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.util.Assert
import java.time.LocalDate

@DataRedisTest
class DealRepositoryTest(
        @Autowired val dealRepository: DealRepository
) {

    @Test
    fun `should add new deal`() {
        val deal: Deal = Deal("dealTest_1", "food", LocalDate.now(), 22.20)
        val newDeal: Deal = dealRepository.save(deal)
        Assert.notNull(newDeal)
    }
}