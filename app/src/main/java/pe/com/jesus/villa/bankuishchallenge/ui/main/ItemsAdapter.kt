package pe.com.jesus.villa.bankuishchallenge.ui.main

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.recyclerview.widget.RecyclerView
import coil.annotation.ExperimentalCoilApi
import com.jesusvilla.domain.Item
import pe.com.jesus.villa.bankuishchallenge.databinding.ViewItemBinding
import pe.com.jesus.villa.bankuishchallenge.ui.common.basicDiffUtil
import pe.com.jesus.villa.bankuishchallenge.ui.common.loadUrl

@ExperimentalCoilApi
class ItemsAdapter(private val listener: (Item) -> Unit) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    var items: List<Item> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ComposeView(parent.context), listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.composeView.disposeComposition()
    }

    @ExperimentalCoilApi
    class ViewHolder(val composeView: ComposeView, private val listener: (Item) -> Unit) :
        RecyclerView.ViewHolder(composeView) {
        init {
            composeView.setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
        }

        fun bind(item: Item) {
            composeView.setContent {
                AndroidViewBinding(
                    factory = ViewItemBinding::inflate,
                    update = {
                        itemTitle.text = item.name
                        item.owner.avatarUrl?.let {
                            itemCover.loadUrl(it)
                        }
                        root.setOnClickListener { listener(item) }
                    }
                )
            }
        }
    }
}