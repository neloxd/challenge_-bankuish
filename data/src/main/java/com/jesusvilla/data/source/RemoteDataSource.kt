package com.jesusvilla.data.source

import com.jesusvilla.domain.Item

interface RemoteDataSource {
    suspend fun getItemsPage(q: String, perPage: String, page: String): List<Item>
}