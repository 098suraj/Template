package com.example.template.data.mainModel


import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName("albums")
    val albums: Albums
) {
    data class Albums(
        @SerializedName("album")
        val album: List<Album>,
        @SerializedName("@attr")
        val attr: Attr
    ) {
        data class Album(
            @SerializedName("artist")
            val artist: Artist,
            @SerializedName("@attr")
            val attr: Attr,
            @SerializedName("image")
            val image: List<Image>,
            @SerializedName("mbid")
            val mbid: String,
            @SerializedName("name")
            val name: String,
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

        data class Attr(
            @SerializedName("page")
            val page: String,
            @SerializedName("perPage")
            val perPage: String,
            @SerializedName("tag")
            val tag: String,
            @SerializedName("total")
            val total: String,
            @SerializedName("totalPages")
            val totalPages: String
        )
    }
}