package pe.com.jesus.villa.bankuishchallenge.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusvilla.data.source.handler.Status
import com.jesusvilla.domain.Item
import pe.com.jesus.villa.bankuishchallenge.ui.common.ScopedViewModel
import com.jesusvilla.usecases.GetItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class MainViewModel @Inject constructor(private val getItems: GetItems) : ScopedViewModel() {

    private val TAG = MainViewModel::class.java.simpleName
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    private val q = "kotlin"
    private val perPage = 10
    private var page by Delegates.notNull<Int>()


    sealed class UiModel {
        object Loading : UiModel()
        class Content(val items: List<Item>) : UiModel()
        class Navigation(val item: Item) : UiModel()
        class Error(val msg: String): UiModel()
        object RequestLocationPermission : UiModel()
    }

    init {
        page = 1
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun getCountPage() = page

    fun onLoadPageRequested() {
        launch {
            Log.i(TAG,"perPage:$perPage - page:$page")
            _model.value = UiModel.Loading
            val value = getItems.invoke(q = q,
                perPage = perPage.toString(),
                page = page.toString()
            )
            when(value.status){
                Status.SUCCESS -> {
                    _model.value = UiModel.Content(value.data!!)
                    page++
                }
                Status.ERROR -> _model.value = UiModel.Error(value.message!!)
                else -> {}
            }
        }
    }

    fun onItemClicked(item: Item) {
        _model.value = UiModel.Navigation(item)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}