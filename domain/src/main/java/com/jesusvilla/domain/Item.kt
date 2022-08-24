package com.jesusvilla.domain

data class Item(
    val id: Long,
    val name: String,
    val full_name: String,
    val owner: Owner,
    val originalTitle: String,
    val private: Boolean,
    val description: String,
    val visibility: String,
    val watchers: Int
)


data class Owner(
    val id: Long?,
    val avatarUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?,
)