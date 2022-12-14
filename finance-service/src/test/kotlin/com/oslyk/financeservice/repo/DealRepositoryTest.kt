package com.oslyk.financeservice.repo

import com.oslyk.financeapi.model.Deal
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.Assert
import java.time.LocalDate

@ActiveProfiles("dev")
@DataRedisTest
class DealRepositoryTest(
        @Autowired val dealRepository: DealRepository
) {

    @Test
    fun `should add new deal`() {
        val deal = Deal("dealTest_1", "food", LocalDate.now(), 22.20)
        val newDeal: Deal = dealRepository.save(deal)
        Assert.notNull(newDeal)
    }

    @Test
    fun `should find deal by name`() {
        val deals: List<Deal> = dealRepository.findAllByName("dealTest_1")
        Assert.notEmpty(deals)
    }
}