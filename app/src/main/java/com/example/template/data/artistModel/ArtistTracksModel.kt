package com.example.template.data.artistModel


import com.google.gson.annotations.SerializedName

data class ArtistTracksModel(
    @SerializedName("toptracks")
    val toptracks: Toptracks
) {
    data class Toptracks(
        @SerializedName("@attr")
        val attr: Attr,
        @SerializedName("track")
        val track: List<Track>
    ) {
        data class Attr(
            @SerializedName("artist")
            val artist: String,
            @SerializedName("page")
            val page: String,
            @SerializedName("perPage")
            val perPage: String,
            @SerializedName("total")
            val total: String,
            @SerializedName("totalPages")
            val totalPages: String
        )

        data class Track(
            @SerializedName("artist")
            val artist: Artist,
            @SerializedName("@attr")
            val attr: Attr,
            @SerializedName("image")
            val image: List<Image>,
            @SerializedName("listeners")
            val listeners: String,
            @SerializedName("mbid")
            val mbid: String?,
            @SerializedName("name")
            val name: String,
            @SerializedName("playcount")
            val playcount: String,
            @SerializedName("streamable")
            val streamable: String,
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
                val rank: String
            )

            data class Image(
                @SerializedName("size")
                val size: String,
                @SerializedName("#text")
                val text: String
            )
        }
    }
}