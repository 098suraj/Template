package com.example.template.data.mainModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistModel(
    @SerialName("topartists")
    val topartists: Topartists
) {
    @Serializable
    data class Topartists(
        @SerialName("artist")
        val artist: List<Artist>,
        @SerialName("@attr")
        val attr: Attr
    ) {
        @Serializable
        data class Artist(
            @SerialName("@attr")
            val attr: Attr,
            @SerialName("image")
            val image: List<Image>,
            @SerialName("mbid")
            val mbid: String?,
            @SerialName("name")
            val name: String,
            @SerialName("streamable")
            val streamable: String,
            @SerialName("url")
            val url: String
        ) {
            @Serializable
            data class Attr(
                @SerialName("rank")
                val rank: String
            )

            @Serializable
            data class Image(
                @SerialName("size")
                val size: String,
                @SerialName("#text")
                val text: String
            )
        }

        @Serializable
        data class Attr(
            @SerialName("page")
            val page: String,
            @SerialName("perPage")
            val perPage: String,
            @SerialName("tag")
            val tag: String,
            @SerialName("total")
            val total: String,
            @SerialName("totalPages")
            val totalPages: String
        )
    }
}