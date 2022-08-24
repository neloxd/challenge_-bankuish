package com.jesusvilla.usecases

import com.jesusvilla.data.repository.ItemsRepository
import com.jesusvilla.domain.Item

class FindItemById(private val itemsRepository: ItemsRepository) {
    suspend fun invoke(id: Long): Item = itemsRepository.findItemById(id)
}