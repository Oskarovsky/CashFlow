package com.oslyk.financeservice.service

import com.oslyk.financeservice.exception.DealNotFoundException
import com.oslyk.financeservice.gateway.FinanceController
import com.oslyk.financeapi.model.Deal

interface DealService {

    @Throws(DealNotFoundException::class)
    fun getDealById(id: String): Deal

    fun getAllDeals(): List<Deal>

    fun createDeal(dealDto: FinanceController.DealDto): Deal

    @Throws(DealNotFoundException::class)
    fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal

    fun updateDeal(deal: Deal): Deal

    @Throws(DealNotFoundException::class)
    fun deleteDeal(id: String)

    fun getDealsByName(name: String): List<Deal>
}