package com.oslyk.financeservice.repo

import com.oslyk.financeapi.model.Deal
import com.oslyk.financeservice.gateway.FinanceController
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Assert
import java.time.LocalDate

@Disabled
@DataRedisTest
@ActiveProfiles(value = ["dev"])
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