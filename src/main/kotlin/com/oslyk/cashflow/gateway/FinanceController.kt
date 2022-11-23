package com.oslyk.cashflow.gateway

import com.oslyk.cashflow.repo.DealRepository
import com.oslyk.cashflow.exception.DealNotFoundException
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.service.DealService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.Date
import javax.validation.Valid
import javax.validation.constraints.PastOrPresent

@RestController
class FinanceController(
        val dealService: DealService
) {

    val logger: Logger = LoggerFactory.getLogger(FinanceController::class.java)

    // region DEAL
    @GetMapping(value = ["/deal"])
    @ResponseStatus(HttpStatus.OK)
    fun getDeals(): List<Deal> {
        return dealService.getAllDeals()
    }

    @GetMapping(value = ["/deal/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getDealById(@PathVariable id: String): Deal {
        logger.info("Fetching Deal with id $id")
        return dealService.getDealById(id)
    }

    @PostMapping(value = ["/deal"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createDeal(@RequestBody dealDto: DealDto): ResponseEntity<Deal> {
        logger.info("Adding new deal finance to database")
        return ResponseEntity(dealService.createDeal(dealDto), HttpStatus.CREATED)
    }

    @PutMapping(value = ["/deal/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun updateDeal(
            @PathVariable id: String,
            @Validated deal: DealDto
): Deal {
        return dealService.updateDeal(id, deal)
    }

    @DeleteMapping(value = ["/deal/{id}"])
    @ResponseStatus(HttpStatus.OK)
    private fun deleteDeal(@PathVariable id: String) {
        dealService.deleteDeal(id)
    }

    // endregion

    data class DealDto(
            @get:NotBlank val name: String?,
            @get:NotBlank val type: String?,
            @get:NotBlank val price: Double?,
            @PastOrPresent val date: LocalDateTime?
    )

    data class ItemDto(
            @get:NotBlank val name: String,
            @get:NotBlank val price: Double
    )
}