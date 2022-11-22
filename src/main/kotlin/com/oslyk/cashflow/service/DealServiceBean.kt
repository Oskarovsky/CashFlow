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
        TODO("Not yet implemented")
    }

    override fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal {
        TODO("Not yet implemented")
    }

    override fun deleteDeal(id: String) {
        TODO("Not yet implemented")
    }

    override fun addItemToDeal(itemId: String, dealId: String): Deal {
        TODO("Not yet implemented")
    }
}