package pe.com.jesus.villa.bankuishchallenge.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface TheItemDbService {

    @GET("search/repositories?")
    suspend fun listItemsPageAsync(
        @Query("q") q: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): ItemDbResult
}