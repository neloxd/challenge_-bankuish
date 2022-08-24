package pe.com.jesus.villa.bankuishchallenge.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jesusvilla.domain.Item
import com.jesusvilla.domain.Owner
import pe.com.jesus.villa.bankuishchallenge.data.database.ItemEntity as DomainItem
import pe.com.jesus.villa.bankuishchallenge.data.server.Item as ServerItem


fun Item.toRoomItem(): DomainItem =
    DomainItem(
        id = id!!,
        name = name!!,
        full_name = full_name!!,
        owner = Gson().toJson(owner),
        originalTitle = originalTitle!!,
        priv = private!!,
        description = description!!,
        visibility = visibility!!,
        watchers = watchers!!
    )

fun DomainItem.toDomainItem(): Item = Item(
    id = id,
    name = name,
    full_name = full_name,
    owner = Gson().fromJson(owner, object : TypeToken<Owner>() {}.type ),
    originalTitle = originalTitle,
    private = priv,
    description = description,
    visibility = visibility,
    watchers = watchers
)

fun ServerItem.toDomainItem(): Item =
    Item(
        id = id!!,
        name = name!!,
        full_name = full_name!!,
        owner = Owner(owner.id, owner.avatarUrl, owner.type, owner.siteAdmin),
        originalTitle = originalTitle!!,
        private = private!!,
        description = description!!,
        visibility = visibility!!,
        watchers = watchers!!
    )