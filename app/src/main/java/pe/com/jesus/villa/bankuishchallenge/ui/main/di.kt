package pe.com.jesus.villa.bankuishchallenge.ui.main

import com.jesusvilla.data.repository.ItemsRepository
import com.jesusvilla.usecases.GetItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getItemsProvider(itemsRepository: ItemsRepository) =
        GetItems(itemsRepository)
}