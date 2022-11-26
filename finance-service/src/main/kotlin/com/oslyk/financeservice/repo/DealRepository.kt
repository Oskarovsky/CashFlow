package com.oslyk.financeservice.repo

import com.oslyk.financeapi.model.Deal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DealRepository: CrudRepository<Deal, String> { }