package com.oslyk.cashflow.service

import com.oslyk.cashflow.exception.ItemNotFoundException
import com.oslyk.cashflow.gateway.FinanceController
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.model.Item


interface ItemService {

    @Throws(ItemNotFoundException::class)
    fun getItem(id: String): Item

    fun getAllItems(): List<Item>

    fun createItem(itemDto: FinanceController.ItemDto): Item

    @Throws(ItemNotFoundException::class)
    fun updateItem(id: String, itemDto: FinanceController.ItemDto): Item

    @Throws(ItemNotFoundException::class)
    fun deleteItem(id: String)

    @Throws
    fun addItemToDeal(itemId: String, dealId: String): Deal
}