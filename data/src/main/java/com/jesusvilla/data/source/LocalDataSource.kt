package com.jesusvilla.data.source

import com.jesusvilla.domain.Item

interface LocalDataSource {
    suspend fun isEmptyItems(): Boolean
    suspend fun saveItems(items: List<Item>)
    suspend fun getListItems(): List<Item>
    suspend fun findItemById(id: Long): Item
    suspend fun updateItem(item: Item)
}