package com.oslyk.cashflow.repo

import com.oslyk.cashflow.model.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: CrudRepository<Item, String> {
}