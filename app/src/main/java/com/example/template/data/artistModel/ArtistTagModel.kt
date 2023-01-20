package com.example.template.data.artistModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistTagModel(
    @SerialName("toptags")
    val toptags: Toptags
) {
    @Serializable
    data class Toptags(
        @SerialName("@attr")
        val attr: Attr,
        @SerialName("tag")
        val tag: List<Tag>
    ) {
        @Serializable
        data class Attr(
            @SerialName("artist")
            val artist: String
        )

        @Serializable
        data class Tag(
            @SerialName("count")
            val count: Int,
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }
}