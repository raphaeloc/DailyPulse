package com.cgs.dailypulse.articles

import com.cgs.dailypulse.serializers.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article (
    @SerialName("title")
    val title: String,

    @SerialName("description")
    val articleDescription: String?,

    @SerialName("publishedAt")
    @Serializable(with = DateSerializer::class)
    val date: String,

    @SerialName("urlToImage")
    val imageUrl: String?
)