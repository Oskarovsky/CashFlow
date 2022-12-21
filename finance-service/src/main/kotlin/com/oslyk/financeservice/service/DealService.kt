package com.oslyk.financeservice.service

import com.oslyk.financeservice.exception.DealNotFoundException
import com.oslyk.financeservice.gateway.FinanceController
import com.oslyk.financeapi.model.Deal
import java.time.LocalDate

interface DealService {

    @Throws(DealNotFoundException::class)
    fun getDealById(id: String): Deal

    fun getAllDeals(startDate: LocalDate?, endDate: LocalDate?): List<Deal>

    fun createDeal(dealDto: FinanceController.DealDto): Deal

    @Throws(DealNotFoundException::class)
    fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal

    fun updateDeal(deal: Deal): Deal

    @Throws(DealNotFoundException::class)
    fun deleteDeal(id: String)

    fun getDealsByName(name: String): List<Deal>

    fun dealTest()
}