package com.oslyk.financeservice.repo

import com.oslyk.cashflow.model.Deal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DealRepository: CrudRepository<Deal, String> { }