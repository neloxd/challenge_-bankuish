package pe.com.jesus.villa.bankuishchallenge.di

import com.jesusvilla.data.repository.ItemsRepository
import com.jesusvilla.data.source.LocalDataSource
import com.jesusvilla.data.source.RemoteDataSource
import com.jesusvilla.data.source.handler.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun itemsRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        responseHandler: ResponseHandler
    ) = ItemsRepository(localDataSource, remoteDataSource, responseHandler)
}