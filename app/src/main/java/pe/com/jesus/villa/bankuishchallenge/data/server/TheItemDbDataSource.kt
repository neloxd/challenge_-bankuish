package pe.com.jesus.villa.bankuishchallenge.data.server

import android.content.Context
import com.jesusvilla.data.source.RemoteDataSource
import com.jesusvilla.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.com.jesus.villa.bankuishchallenge.data.toDomainItem

class TheItemDbDataSource(val context: Context) : RemoteDataSource {

    override suspend fun getItemsPage(q: String, perPage: String, page: String): List<Item> =
        withContext(Dispatchers.IO) {
            val list = TheItemDb(context = context).service
                .listItemsPageAsync(q, perPage,page)
                .items

            println("list:${list.size}")

            list.map {
                it.toDomainItem()
            }
        }

}