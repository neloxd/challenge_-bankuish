package com.jesusvilla.data.repository

import com.jesusvilla.data.source.LocalDataSource
import com.jesusvilla.data.source.RemoteDataSource
import com.jesusvilla.data.source.handler.Resource
import com.jesusvilla.data.source.handler.ResponseHandler
import com.jesusvilla.domain.Item

class ItemsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val responseHandler: ResponseHandler
) {

    suspend fun getListPage(q: String, perPage: String, page: String): Resource<List<Item>> {
        return try {
            println("getListPage:$q - $perPage - $page")
            if (page == "1" && localDataSource.isEmptyItems()) {
                val items = remoteDataSource.getItemsPage(q, perPage, page)
                println("getListPage items:${items.size}")
                localDataSource.saveItems(items)
            }
            responseHandler.handleSuccess(localDataSource.getListItems())
        } catch (e: Exception) {
            println("Exception:$e")
            responseHandler.handleException(e)
        }
    }

    suspend fun findItemById(id: Long): Item = localDataSource.findItemById(id)

    suspend fun update(item: Item) = localDataSource.updateItem(item)
}

interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}