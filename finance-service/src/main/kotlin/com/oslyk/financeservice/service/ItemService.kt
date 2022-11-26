package com.oslyk.financeservice.service

import com.oslyk.financeservice.exception.ItemNotFoundException
import com.oslyk.financeservice.gateway.FinanceController
import com.oslyk.financeapi.model.Deal
import com.oslyk.financeapi.model.Item


interface ItemService {

    @Throws(ItemNotFoundException::class)
    fun getItem(id: String): Item

    fun getAllItems(): List<Item>

    fun createItem(itemDto: FinanceController.ItemDto): Item

    fun createItemForDeal(dealId: String, itemDto: FinanceController.ItemDto): Item

    @Throws(ItemNotFoundException::class)
    fun updateItem(id: String, itemDto: FinanceController.ItemDto): Item

    @Throws(ItemNotFoundException::class)
    fun deleteItem(id: String)

    @Throws
    fun addItemToDeal(itemId: String, dealId: String): Deal
}