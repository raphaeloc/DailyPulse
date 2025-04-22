package com.cgs.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article (
    @SerialName("title")
    val title: String,

    @SerialName("description")
    val articleDescription: String?,

    @SerialName("publishedAt")
    val date: String,

    @SerialName("urlToImage")
    val imageUrl: String?
)