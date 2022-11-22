package com.oslyk.cashflow.gateway

import com.oslyk.cashflow.repo.DealRepository
import com.oslyk.cashflow.exception.DealNotFoundException
import com.oslyk.cashflow.model.Deal
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class FinanceController(val dealRepository: DealRepository) {

    val logger = LoggerFactory.getLogger(FinanceController::class.java)

    private var deals = mutableListOf<Deal>(
            Deal(1, "Transaction_1", 33.10),
            Deal(2, "Transaction_2", 13.99),
            Deal(3, "Transaction_3", 35.00)
    )

    @GetMapping("/")
    fun getMain() = "Main page"

    @GetMapping("/deal")
    fun getDeals(): ResponseEntity<MutableIterable<Deal>> {
        logger.info("Fetching all deals")
        return ResponseEntity<MutableIterable<Deal>>(dealRepository.findAll(), HttpStatus.OK)
    }

    @GetMapping("/deal/{id}")
    fun getDealById(@PathVariable id: Int): Deal? {
        val deal = deals.firstOrNull { it.id == id }
        return deal ?: throw DealNotFoundException()
    }

    @PostMapping("/deal")
    @ResponseStatus(HttpStatus.CREATED)
    fun addDeal(@RequestBody deal: Deal): ResponseEntity<Deal> {
        logger.info("Adding new deal finance to database")
        dealRepository.save(deal)
        return ResponseEntity(deal, HttpStatus.CREATED)
    }
}