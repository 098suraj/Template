package com.example.template.data.mainModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagModel(
    @SerialName("toptags")
    val toptags: Toptags
) {
    @Serializable
    data class Toptags(
        @SerialName("tag")
        val tag: List<Tag>
    ) {
        @Serializable
        data class Tag(
            @SerialName("count")
            val count: Int,
            @SerialName("name")
            val name: String,
            @SerialName("reach")
            val reach: Int
        )
    }
}