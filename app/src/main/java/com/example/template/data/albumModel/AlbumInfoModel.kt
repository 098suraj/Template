package com.example.template.data.albumModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumInfoModel(
    @SerialName("album")
    val album: Album
) {
    @Serializable
    data class Album(
        @SerialName("artist")
        val artist: String,
        @SerialName("image")
        val image: List<Image>,
        @SerialName("listeners")
        val listeners: String,
        @SerialName("name")
        val name: String,
        @SerialName("playcount")
        val playcount: String,
        @SerialName("tags")
        val tags: Tags,
        @SerialName("tracks")
        val tracks: Tracks,
        @SerialName("url")
        val url: String,
        @SerialName("wiki")
        val wiki: Wiki
    ) {
        @Serializable
        data class Image(
            @SerialName("size")
            val size: String,
            @SerialName("#text")
            val text: String
        )

        @Serializable
        data class Tags(
            @SerialName("tag")
            val tag: List<Tag>
        ) {
            @Serializable
            data class Tag(
                @SerialName("name")
                val name: String,
                @SerialName("url")
                val url: String
            )
        }

        @Serializable
        data class Tracks(
            @SerialName("track")
            val track: List<Track>
        ) {
            @Serializable
            data class Track(
                @SerialName("artist")
                val artist: Artist,
                @SerialName("@attr")
                val attr: Attr,
                @SerialName("duration")
                val duration: Int?,
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
                    val rank: Int
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

        @Serializable
        data class Wiki(
            @SerialName("content")
            val content: String,
            @SerialName("published")
            val published: String,
            @SerialName("summary")
            val summary: String
        )
    }
}