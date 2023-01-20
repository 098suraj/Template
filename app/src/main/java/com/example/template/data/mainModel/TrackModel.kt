package com.example.template.data.mainModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackModel(
    @SerialName("tracks")
    val tracks: Tracks
) {
    @Serializable
    data class Tracks(
        @SerialName("@attr")
        val attr: Attr,
        @SerialName("track")
        val track: List<Track>
    ) {
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

        @Serializable
        data class Track(
            @SerialName("artist")
            val artist: Artist,
            @SerialName("@attr")
            val attr: Attr,
            @SerialName("duration")
            val duration: String,
            @SerialName("image")
            val image: List<Image>,
            @SerialName("mbid")
            val mbid: String,
            @SerialName("name")
            val name: String,
            @SerialName("streamable")
            val streamable: Streamable,
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

            @Serializable
            data class Streamable(
                @SerialName("fulltrack")
                val fulltrack: String,
                @SerialName("#text")
                val text: String
            )
        }
    }
}