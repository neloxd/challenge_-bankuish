package pe.com.jesus.villa.bankuishchallenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusvilla.domain.Item
import pe.com.jesus.villa.bankuishchallenge.ui.common.ScopedViewModel
import com.jesusvilla.usecases.FindItemById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("itemId") private val itemId: Long,
    private val findItemById: FindItemById) :
    ScopedViewModel() {

    class UiModel(val item: Item)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findItem()
            return _model
        }

    private fun findItem() = launch {
        _model.value = UiModel(findItemById.invoke(itemId))
    }
}