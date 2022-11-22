package com.oslyk.cashflow.gateway

import com.oslyk.cashflow.repo.DealRepository
import com.oslyk.cashflow.exception.DealNotFoundException
import com.oslyk.cashflow.model.Deal
import com.oslyk.cashflow.service.DealService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import javax.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class FinanceController(
        val dealRepository: DealRepository,
        val dealService: DealService
) {

    val logger = LoggerFactory.getLogger(FinanceController::class.java)

    @GetMapping("/")
    fun getMain() = "Main page"

    @GetMapping("/deal")
    fun getDeals(): ResponseEntity<List<Deal>> {
        logger.info("Fetching all deals")
        return ResponseEntity<List<Deal>>(dealService.getAllDeals(), HttpStatus.OK)
    }

    @GetMapping("/deal/{id}")
    fun getDealById(@PathVariable id: String): Deal? {
        return dealService.getDealById(id)
    }

    @PostMapping("/deal")
    @ResponseStatus(HttpStatus.CREATED)
    fun addDeal(@RequestBody deal: Deal): ResponseEntity<Deal> {
        logger.info("Adding new deal finance to database")
        dealRepository.save(deal)
        return ResponseEntity(deal, HttpStatus.CREATED)
    }

    data class DealDto(
            @get:NotBlank val name: String?,
            @get:NotBlank val type: String?,
            @get:NotBlank val price: Double?,
            val date: Date
    )
}