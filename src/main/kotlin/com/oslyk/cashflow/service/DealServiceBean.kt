package com.oslyk.cashflow.service

import com.oslyk.cashflow.exception.DealNotFoundException
import com.oslyk.cashflow.gateway.FinanceController
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.repo.DealRepository
import org.springframework.stereotype.Service

@Service
class DealServiceBean(val dealRepository: DealRepository): DealService {

    override fun getDealById(id: String): Deal {
        return dealRepository
                .findById(id)
                .orElseThrow { DealNotFoundException("Unable to find movie for id $id") }
    }

    override fun getAllDeals(): List<Deal> {
        return dealRepository
                .findAll()
                .toList()
    }

    override fun createDeal(dealDto: FinanceController.DealDto): Deal {
        return dealRepository
                .save(Deal(name = dealDto.name.orEmpty(), type = dealDto.type.orEmpty(), date = dealDto.date, price = dealDto.price ?: 0.0))
    }

    override fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal {
        val deal: Deal = dealRepository
                .findById(id)
                .orElseThrow { DealNotFoundException("Could not find Deal with $id") }

        val updatedDeal = deal.copy(
                name = dealDto.name.orEmpty(),
                type = dealDto.type.orEmpty(),
                date = dealDto.date,
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
}