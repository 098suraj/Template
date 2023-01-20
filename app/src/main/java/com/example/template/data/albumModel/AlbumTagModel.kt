package com.example.template.data.albumModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumTagModel(
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
            @SerialName("album")
            val album: String,
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