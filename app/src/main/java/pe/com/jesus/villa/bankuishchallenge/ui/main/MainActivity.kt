package pe.com.jesus.villa.bankuishchallenge.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.annotation.ExperimentalCoilApi
import pe.com.jesus.villa.bankuishchallenge.databinding.ActivityMainBinding
import pe.com.jesus.villa.bankuishchallenge.ui.common.PermissionRequester
import pe.com.jesus.villa.bankuishchallenge.ui.common.startActivity
import pe.com.jesus.villa.bankuishchallenge.ui.detail.DetailActivity
import pe.com.jesus.villa.bankuishchallenge.ui.main.MainViewModel.UiModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemsAdapter
    private val TAG = "MainActivity"
    private val coarsePermissionRequester =
        PermissionRequester(this, ACCESS_COARSE_LOCATION)
    private var isLocationRequest = false

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemsAdapter(viewModel::onItemClicked)
        binding.recycler.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
        binding.btnConnection.setOnClickListener {retryConnection()}
        setUpScroll()
    }

    private fun setUpScroll() {
        binding.recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            run {
                if (v == binding.recycler) {
                    if (binding.recycler.getChildAt(binding.recycler.childCount - 1) != null) {
                        if ((scrollY >= (binding.recycler.getChildAt(binding.recycler.childCount - 1)
                                .measuredHeight - binding.recycler.measuredHeight)) &&
                            scrollY > oldScrollY
                        ) {
                            if(viewModel.getCountPage() > 1)
                                showLoadMore(true)
                            viewModel.onLoadPageRequested()
                        }
                    }
                }
            }
        }
    }

    private fun showLoadMore(show: Boolean) {
        binding.loaderProgress.isVisible = show
    }

    private fun updateUi(model: UiModel) {

        binding.progress.visibility = if (model is UiModel.Loading && viewModel.getCountPage() == 1) View.VISIBLE else View.GONE
        binding.recycler.visibility = if (model is UiModel.Content || model is UiModel.Navigation) View.VISIBLE else View.GONE
        binding.btnConnection.visibility = if (model is UiModel.Error) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> {
                showLoadMore(false)
                adapter.items = adapter.items.plus(model.items)
            }
            is UiModel.Error -> {
                Toast.makeText(this@MainActivity, model.msg, Toast.LENGTH_LONG).show()
                showLoadMore(false)
            }
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.ITEM, model.item.id)
            }
            UiModel.RequestLocationPermission -> syncRequest()
            else -> {}
        }
    }

    private fun syncRequest(){
        coarsePermissionRequester.request {
            if(it){
                viewModel.onLoadPageRequested()
            }else{
                isLocationRequest = true
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri: Uri = Uri.fromParts("package", this@MainActivity.packageName, null)
                intent.data = uri
                this@MainActivity.startActivity(intent)
            }
        }
    }

    private fun retryConnection(){
        syncRequest()
    }

    override fun onResume() {
        super.onResume()
        if(isLocationRequest){
            isLocationRequest = false
            Toast.makeText(this, "Es obligatorio los permisos de Acceso a la ubicación por favor", Toast.LENGTH_SHORT).show()
            syncRequest()
        }
    }
}