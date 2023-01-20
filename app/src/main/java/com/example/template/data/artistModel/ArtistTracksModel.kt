package com.example.template.data.artistModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistTracksModel(
    @SerialName("toptracks")
    val toptracks: Toptracks
) {
    @Serializable
    data class Toptracks(
        @SerialName("@attr")
        val attr: Attr,
        @SerialName("track")
        val track: List<Track>
    ) {
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

        @Serializable
        data class Track(
            @SerialName("artist")
            val artist: Artist,
            @SerialName("@attr")
            val attr: Attr,
            @SerialName("image")
            val image: List<Image>,
            @SerialName("listeners")
            val listeners: String,
            @SerialName("mbid")
            val mbid: String?,
            @SerialName("name")
            val name: String,
            @SerialName("playcount")
            val playcount: String,
            @SerialName("streamable")
            val streamable: String,
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
    }
}