package com.example.rynnarriola.newsapp.data.model

import com.google.gson.annotations.SerializedName

class Article {
    data class Article(
        @SerializedName("title")
        val title: String = "",
        @SerializedName("description")
        val description: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("urlToImage")
        val imageUrl: String = "",
        @SerializedName("source")
        val source: Source
    )
}