package pe.com.jesus.villa.bankuishchallenge.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.jesusvilla.domain.Item


class ItemDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setItem(item: Item) = with(item) {
        text = buildSpannedString {

            bold { append("Full Name: ") }
            appendLine(full_name)

            bold { append("Original title: ") }
            appendLine(originalTitle)

            bold { append("Description: ") }
            appendLine(description)

            bold { append("Private: ") }
            appendLine(private.toString())

            bold { append("Watches: ") }
            append(watchers.toString())
        }
    }
}