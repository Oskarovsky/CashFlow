package com.oslyk.cashflow.service

import com.oslyk.cashflow.exception.DealNotFoundException
import com.oslyk.cashflow.exception.ItemNotFoundException
import com.oslyk.cashflow.gateway.FinanceController
import com.oslyk.cashflow.model.Deal

interface DealService {

    @Throws(DealNotFoundException::class)
    fun getDealById(id: String): Deal

    fun getAllDeals(): List<Deal>

    fun createDeal(dealDto: FinanceController.DealDto): Deal

    @Throws(DealNotFoundException::class)
    fun updateDeal(id: String, dealDto: FinanceController.DealDto): Deal

    @Throws(DealNotFoundException::class)
    fun deleteDeal(id: String)

    @Throws(ItemNotFoundException::class, DealNotFoundException::class)
    fun addItemToDeal(itemId: String, dealId: String): Deal
}