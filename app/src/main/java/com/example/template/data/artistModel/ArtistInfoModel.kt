package com.example.template.data.artistModel


import com.google.gson.annotations.SerializedName

data class ArtistInfoModel(
    @SerializedName("artist")
    val artist: Artist
) {
    data class Artist(
        @SerializedName("bio")
        val bio: Bio,
        @SerializedName("image")
        val image: List<Image>,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("ontour")
        val ontour: String,
        @SerializedName("similar")
        val similar: Similar,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("streamable")
        val streamable: String,
        @SerializedName("tags")
        val tags: Tags,
        @SerializedName("url")
        val url: String
    ) {
        data class Bio(
            @SerializedName("content")
            val content: String,
            @SerializedName("links")
            val links: Links,
            @SerializedName("published")
            val published: String,
            @SerializedName("summary")
            val summary: String
        ) {
            data class Links(
                @SerializedName("link")
                val link: Link
            ) {
                data class Link(
                    @SerializedName("href")
                    val href: String,
                    @SerializedName("rel")
                    val rel: String,
                    @SerializedName("#text")
                    val text: String
                )
            }
        }

        data class Image(
            @SerializedName("size")
            val size: String,
            @SerializedName("#text")
            val text: String
        )

        data class Similar(
            @SerializedName("artist")
            val artist: List<Artist>
        ) {
            data class Artist(
                @SerializedName("image")
                val image: List<Image>,
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
            ) {
                data class Image(
                    @SerializedName("size")
                    val size: String,
                    @SerializedName("#text")
                    val text: String
                )
            }
        }

        data class Stats(
            @SerializedName("listeners")
            val listeners: String,
            @SerializedName("playcount")
            val playcount: String
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
    }
}