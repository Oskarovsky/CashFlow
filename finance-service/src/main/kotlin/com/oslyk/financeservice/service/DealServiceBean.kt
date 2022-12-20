package com.oslyk.financeservice.service

import com.oslyk.financeservice.exception.DealNotFoundException
import com.oslyk.financeservice.gateway.FinanceController
import com.oslyk.financeapi.model.Deal
import com.oslyk.financeservice.repo.DealRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DealServiceBean(
        val dealRepository: DealRepository,
        val redisTemplate: RedisTemplate<String, String>): DealService {

    override fun getDealById(id: String): Deal {
        return dealRepository
                .findById(id)
                .orElseThrow { DealNotFoundException("Unable to find movie for id $id") }
    }

    override fun getAllDeals(startDate: LocalDate?, endDate: LocalDate?): List<Deal> {
        return if (startDate != null && endDate != null) {
            dealRepository.findAllByDateBetween(startDate, endDate).toList()
        } else if (startDate != null) {
            dealRepository.findAllByDateAfter(startDate).toList()
        } else if (endDate != null) {
            dealRepository.findAllByDateBefore(endDate).toList()
        } else {
            dealRepository.findAll().toList()
        }
    }

    override fun createDeal(dealDto: FinanceController.DealDto): Deal {
        return dealRepository
                .save(Deal(name = dealDto.name.orEmpty(), type = dealDto.type.orEmpty(), date = dealDto.date ?: LocalDate.now(), price = dealDto.price ?: 0.0))
    }

    override fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal {
        val deal: Deal = dealRepository
                .findById(id)
                .orElseThrow { DealNotFoundException("Could not find Deal with $id") }

        val updatedDeal = deal.copy(
                name = dealDto.name.orEmpty(),
                type = dealDto.type.orEmpty(),
                date = dealDto.date ?: deal.date,
                price = dealDto.price ?: 0.0
        )

        updatedDeal.id = id
        return dealRepository.save(updatedDeal)
    }

    override fun updateDeal(deal: Deal): Deal {
        return dealRepository.save(deal)
    }

    override fun deleteDeal(id: String) {
        dealRepository.delete(getDealById(id))
    }

    override fun getDealsByName(name: String): List<Deal> {
        return dealRepository.findAllByName(name)
    }
}