package pe.com.jesus.villa.bankuishchallenge.data.database

import android.util.Log
import com.jesusvilla.data.source.LocalDataSource
import com.jesusvilla.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.com.jesus.villa.bankuishchallenge.data.toDomainItem
import pe.com.jesus.villa.bankuishchallenge.data.toRoomItem

class RoomDataSource(db: ItemDatabase) : LocalDataSource {

    private val itemDao = db.itemDao()

    override suspend fun isEmptyItems(): Boolean =
        withContext(Dispatchers.IO) { itemDao.itemCount() <= 0 }

    override suspend fun saveItems(items: List<Item>) {
        Log.i("RoomDataSource","saveItems:$items")
        withContext(Dispatchers.IO) { itemDao.insertItems(items.map { it.toRoomItem() }) }
    }

    override suspend fun getListItems(): List<Item>  =
        itemDao.getAllItems().map { it.toDomainItem() }

    override suspend fun findItemById(id: Long): Item = withContext(Dispatchers.IO) {
        itemDao.findItemById(id).toDomainItem()
    }

    override suspend fun updateItem(item: Item) {
        withContext(Dispatchers.IO) { itemDao.updateItem(item.toRoomItem()) }
    }
}