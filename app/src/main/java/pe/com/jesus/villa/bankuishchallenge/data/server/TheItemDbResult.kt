package pe.com.jesus.villa.bankuishchallenge.data.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Jesús Villa on 23/08/22
 */

data class ItemDbResult(
    val items: List<Item>,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean
)

@Parcelize
data class Item(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("language") val originalTitle: String?,
    @SerializedName("private") val private: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("visibility") val visibility: String?,
    @SerializedName("watchers_count") val watchers: Int?
) : Parcelable

@Parcelize
data class Owner(
    @SerializedName("id") val id: Long?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("site_admin") val siteAdmin: Boolean?,
) : Parcelable