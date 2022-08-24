package com.jesusvilla.usecases

import com.jesusvilla.data.repository.ItemsRepository
import com.jesusvilla.data.source.handler.Resource
import com.jesusvilla.domain.Item

class GetItems(private val itemsRepository: ItemsRepository) {
    suspend fun invoke(q: String, perPage: String, page: String): Resource<List<Item>> =
        itemsRepository.getListPage(q, perPage, page)
}