package pe.com.jesus.villa.bankuishchallenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val full_name: String,
    val owner: String,
    val originalTitle: String,
    val priv: Boolean,
    val description: String,
    val visibility: String,
    val watchers: Int
)

@Entity
data class Owner(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val avatarUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?,
)