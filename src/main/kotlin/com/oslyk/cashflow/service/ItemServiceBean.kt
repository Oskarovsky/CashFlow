package com.oslyk.cashflow.service

import com.oslyk.cashflow.exception.ItemNotFoundException
import com.oslyk.cashflow.gateway.FinanceController
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.model.Item
import com.oslyk.cashflow.repo.DealRepository
import com.oslyk.cashflow.repo.ItemRepository
import org.springframework.stereotype.Service

@Service
class ItemServiceBean(
        val itemRepository: ItemRepository,
        val dealService: DealService
): ItemService {

    override fun getItem(id: String): Item {
       return itemRepository
               .findById(id)
               .orElseThrow { ItemNotFoundException("Could not find Item with id $id") }
    }

    override fun getAllItems(): List<Item> {
        return itemRepository.findAll().toList()
    }

    override fun createItem(itemDto: FinanceController.ItemDto): Item {
        return itemRepository.save(Item(name = itemDto.name.orEmpty(), price = itemDto.price ?: 0.0))
    }

    override fun updateItem(id: String, itemDto: FinanceController.ItemDto): Item {
        val item = getItem(id).copy(name = itemDto.name, price = itemDto.price)
        item.id = id
        return itemRepository.save(item)
    }

    override fun deleteItem(id: String) {
        itemRepository.deleteById(id)
    }

    override fun addItemToDeal(itemId: String, dealId: String): Deal {
        val deal: Deal = dealService.getDealById(dealId)
        val item: Item = getItem(itemId)
        (deal.items as ArrayList).add(item)
        return dealService.updateDeal(deal)
    }

}