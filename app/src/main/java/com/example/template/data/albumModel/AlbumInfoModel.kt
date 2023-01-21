package com.example.template.data.albumModel


import com.google.gson.annotations.SerializedName

data class AlbumInfoModel(
    @SerializedName("album")
    val album: Album
) {
    data class Album(
        @SerializedName("artist")
        val artist: String,
        @SerializedName("image")
        val image: List<Image>,
        @SerializedName("listeners")
        val listeners: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("playcount")
        val playcount: String,
        @SerializedName("tags")
        val tags: Tags,
        @SerializedName("tracks")
        val tracks: Tracks,
        @SerializedName("url")
        val url: String,
        @SerializedName("wiki")
        val wiki: Wiki
    ) {
        data class Image(
            @SerializedName("size")
            val size: String,
            @SerializedName("#text")
            val text: String
        )

        data class Tags(
            @SerializedName("tag")
            val tag: List<Tag>
        ) {
            data class Tag(
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
            )
        }

        data class Tracks(
            @SerializedName("track")
            val track: List<Track>
        ) {
            data class Track(
                @SerializedName("artist")
                val artist: Artist,
                @SerializedName("@attr")
                val attr: Attr,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("streamable")
                val streamable: Streamable,
                @SerializedName("url")
                val url: String
            ) {
                data class Artist(
                    @SerializedName("mbid")
                    val mbid: String,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("url")
                    val url: String
                )

                data class Attr(
                    @SerializedName("rank")
                    val rank: Int
                )

                data class Streamable(
                    @SerializedName("fulltrack")
                    val fulltrack: String,
                    @SerializedName("#text")
                    val text: String
                )
            }
        }

        data class Wiki(
            @SerializedName("content")
            val content: String,
            @SerializedName("published")
            val published: String,
            @SerializedName("summary")
            val summary: String
        )
    }
}