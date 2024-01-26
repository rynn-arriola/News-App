package com.example.rynnarriola.newsapp.data.model

import com.example.rynnarriola.newsapp.local.entity.Source
import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    return Source(id, name)
}