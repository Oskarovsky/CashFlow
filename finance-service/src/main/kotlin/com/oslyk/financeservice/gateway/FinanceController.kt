package com.oslyk.financeservice.gateway

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.model.Item
import com.oslyk.cashflow.service.DealService
import com.oslyk.cashflow.service.ItemService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import javax.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.constraints.Past

@RestController
@RequestMapping("/api/v1")
class FinanceController(
        val dealService: DealService,
        val itemService: ItemService
) {

    val logger: Logger = LoggerFactory.getLogger(FinanceController::class.java)

    // region DEAL
    @GetMapping(value = ["/deal"])
    @ResponseStatus(HttpStatus.OK)
    fun getDeals(): List<Deal> {
        return dealService.getAllDeals()
    }

    @GetMapping(value = ["/deal/{dealId}"])
    @ResponseStatus(HttpStatus.OK)
    fun getDealById(@PathVariable dealId: String): Deal {
        logger.info("Fetching Deal with id $dealId")
        return dealService.getDealById(dealId)
    }

    @PostMapping(value = ["/deal"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeal(@RequestBody dealDto: DealDto): ResponseEntity<Deal> {
        logger.info("Adding new deal finance to database")
        return ResponseEntity(dealService.createDeal(dealDto), HttpStatus.CREATED)
    }

    @PutMapping(value = ["/deal/{dealId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateDeal(
            @PathVariable dealId: String,
            @Validated @RequestBody deal: DealDto
    ): Deal {
        return dealService.updateDeal(dealId, deal)
    }

    @DeleteMapping(value = ["/deal/{dealId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun deleteDeal(@PathVariable dealId: String) {
        dealService.deleteDeal(dealId)
    }
    // endregion

    // region ITEM
    @GetMapping("/item")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllItems(): List<Item> {
        return itemService.getAllItems()
    }

    @PostMapping(value = ["/deal/{dealId}/item"])
    @ResponseStatus(HttpStatus.CREATED)
    private fun addItem(@PathVariable dealId: String,
                        @RequestBody itemDto: ItemDto
    ): Item {
        return itemService.createItemForDeal(dealId, itemDto)
    }

    @DeleteMapping(value = ["/deal/{dealId}/item/{itemId}"])
    @ResponseStatus(HttpStatus.OK)
    private fun removeItemFromDeal(
            @PathVariable dealId: String,
            @PathVariable itemId: String)
    {
        itemService.deleteItem(itemId)
    }

    // endregion

    data class DealDto(
            @get:NotBlank val name: String?,
            val type: String?,
            val price: Double?,
            @field:DateTimeFormat(pattern = "yyyy-MM-dd")
            @field:JsonDeserialize(using = LocalDateDeserializer::class)
            @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @get:Past val date: LocalDate?
    )

    data class ItemDto(
            @get:NotBlank val name: String,
            @get:NotBlank val price: Double
    )
}