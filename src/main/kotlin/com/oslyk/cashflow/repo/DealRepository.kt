package com.oslyk.cashflow.repo

import com.oslyk.cashflow.model.Deal
import org.springframework.data.repository.CrudRepository

interface DealRepository: CrudRepository<Deal, Int> { }