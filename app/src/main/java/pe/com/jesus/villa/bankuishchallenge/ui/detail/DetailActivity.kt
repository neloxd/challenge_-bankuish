package pe.com.jesus.villa.bankuishchallenge.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import pe.com.jesus.villa.bankuishchallenge.databinding.ActivityDetailBinding
import pe.com.jesus.villa.bankuishchallenge.ui.common.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM = "DetailActivity:item"
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(binding) {
        val item = model.item
        //Toast.makeText(this@DetailActivity, movie.toString(), Toast.LENGTH_LONG).show()
        itemDetailToolbar.title = item.name
        item.owner.avatarUrl?.let {
            itemDetailImage.loadUrl(it)
        }
        itemDetailSummary.text = item.owner.type
        itemDetailInfo.setItem(item)
    }
}