package com.example.template.data.artistModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistInfoModel(
    @SerialName("artist")
    val artist: Artist
) {
    @Serializable
    data class Artist(
        @SerialName("bio")
        val bio: Bio,
        @SerialName("image")
        val image: List<Image>,
        @SerialName("mbid")
        val mbid: String,
        @SerialName("name")
        val name: String,
        @SerialName("ontour")
        val ontour: String,
        @SerialName("similar")
        val similar: Similar,
        @SerialName("stats")
        val stats: Stats,
        @SerialName("streamable")
        val streamable: String,
        @SerialName("tags")
        val tags: Tags,
        @SerialName("url")
        val url: String
    ) {
        @Serializable
        data class Bio(
            @SerialName("content")
            val content: String,
            @SerialName("links")
            val links: Links,
            @SerialName("published")
            val published: String,
            @SerialName("summary")
            val summary: String
        ) {
            @Serializable
            data class Links(
                @SerialName("link")
                val link: Link
            ) {
                @Serializable
                data class Link(
                    @SerialName("href")
                    val href: String,
                    @SerialName("rel")
                    val rel: String,
                    @SerialName("#text")
                    val text: String
                )
            }
        }

        @Serializable
        data class Image(
            @SerialName("size")
            val size: String,
            @SerialName("#text")
            val text: String
        )

        @Serializable
        data class Similar(
            @SerialName("artist")
            val artist: List<Artist>
        ) {
            @Serializable
            data class Artist(
                @SerialName("image")
                val image: List<Image>,
                @SerialName("name")
                val name: String,
                @SerialName("url")
                val url: String
            ) {
                @Serializable
                data class Image(
                    @SerialName("size")
                    val size: String,
                    @SerialName("#text")
                    val text: String
                )
            }
        }

        @Serializable
        data class Stats(
            @SerialName("listeners")
            val listeners: String,
            @SerialName("playcount")
            val playcount: String
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
    }
}