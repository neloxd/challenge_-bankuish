package pe.com.jesus.villa.bankuishchallenge.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.jesusvilla.data.repository.ItemsRepository
import com.jesusvilla.usecases.FindItemById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailActivityModule {

    @Provides
    fun findItemByIdProvider(itemsRepository: ItemsRepository) = FindItemById(itemsRepository)

    @Provides
    @Named("itemId")
    fun itemIdProvider(stateHandle: SavedStateHandle): Long =
        stateHandle.get<Long>(DetailActivity.ITEM)
            ?: throw IllegalStateException("Item Id not found in the state handle")
}