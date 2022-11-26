package com.oslyk.financeservice.service

import com.oslyk.financeservice.exception.ItemNotFoundException
import com.oslyk.financeservice.gateway.FinanceController
import com.oslyk.financeapi.model.Deal
import com.oslyk.financeapi.model.Item
import com.oslyk.financeservice.repo.ItemRepository
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

    override fun createItemForDeal(dealId: String, itemDto: FinanceController.ItemDto): Item {
        val deal: Deal = dealService.getDealById(dealId)
        val item: Item = createItem(itemDto)
        (deal.items as ArrayList).add(item)
        dealService.updateDeal(deal)
        return item
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