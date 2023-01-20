package com.example.template.data.mainModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoModel(
    @SerialName("tag")
    val tag: Tag
) {
    @Serializable
    data class Tag(
        @SerialName("name")
        val name: String,
        @SerialName("reach")
        val reach: Int,
        @SerialName("total")
        val total: Int,
        @SerialName("wiki")
        val wiki: Wiki
    ) {
        @Serializable
        data class Wiki(
            @SerialName("content")
            val content: String,
            @SerialName("summary")
            val summary: String
        )
    }
}