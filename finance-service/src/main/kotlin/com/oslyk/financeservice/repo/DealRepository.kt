package com.oslyk.financeservice.repo

import com.oslyk.financeapi.model.Deal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DealRepository: CrudRepository<Deal, String> {

    fun findAllByName(name: String): List<Deal>

    fun findAllByDateBetween(startDate: LocalDate, endDate: LocalDate): List<Deal>

    fun findAllByDateAfter(startDate: LocalDate): List<Deal>

    fun findAllByDateBefore(endDate: LocalDate): List<Deal>
}