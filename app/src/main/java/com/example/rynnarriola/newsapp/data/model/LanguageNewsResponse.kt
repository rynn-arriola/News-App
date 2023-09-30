package com.example.rynnarriola.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class LanguageNewsResponse(
    @SerializedName("sources")
    val sources: List<LanguageSource> = ArrayList(),
    @SerializedName("status")
    val status: String = "",
)