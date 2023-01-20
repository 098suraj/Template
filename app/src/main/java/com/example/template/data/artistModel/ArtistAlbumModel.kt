package com.example.template.data.artistModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistAlbumModel(
    @SerialName("topalbums")
    val topalbums: Topalbums
) {
    @Serializable
    data class Topalbums(
        @SerialName("album")
        val album: List<Album>,
        @SerialName("@attr")
        val attr: Attr
    ) {
        @Serializable
        data class Album(
            @SerialName("artist")
            val artist: Artist,
            @SerialName("image")
            val image: List<Image>,
            @SerialName("mbid")
            val mbid: String?,
            @SerialName("name")
            val name: String,
            @SerialName("playcount")
            val playcount: Int,
            @SerialName("url")
            val url: String
        ) {
            @Serializable
            data class Artist(
                @SerialName("mbid")
                val mbid: String,
                @SerialName("name")
                val name: String,
                @SerialName("url")
                val url: String
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
            @SerialName("artist")
            val artist: String,
            @SerialName("page")
            val page: String,
            @SerialName("perPage")
            val perPage: String,
            @SerialName("total")
            val total: String,
            @SerialName("totalPages")
            val totalPages: String
        )
    }
}