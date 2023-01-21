package com.example.template.remote

import com.example.template.BuildConfig
import com.example.template.data.albumModel.AlbumInfoModel
import com.example.template.data.mainModel.AlbumModel
import com.example.template.data.albumModel.AlbumTagModel
import com.example.template.data.artistModel.ArtistAlbumModel
import com.example.template.data.artistModel.ArtistInfoModel
import com.example.template.data.artistModel.ArtistTagModel
import com.example.template.data.artistModel.ArtistTracksModel
import com.example.template.data.mainModel.ArtistModel
import com.example.template.data.mainModel.InfoModel
import com.example.template.data.mainModel.TagModel
import com.example.template.data.mainModel.TrackModel
import com.example.template.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/2.0/")
    suspend fun getTags(
        @Query("method") method: String = "tag.getTopTags",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json"
    ): Response<TagModel>

    @GET("/2.0/")
    suspend fun getAlbum(
        @Query("method") method: String = "tag.gettopalbums",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("tag") tag: String ,
        @Query("format") format: String = "json",

        ): Response<AlbumModel>

    @GET("/2.0/")
    suspend fun getArtist(
        @Query("method") method: String = "tag.gettopartists",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("tag") tag: String,
        @Query("format") format: String = "json",
    ): Response<ArtistModel>

    @GET("/2.0/")
    suspend fun getTracks(
        @Query("method") method: String = "tag.gettoptracks",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("tag") tag: String ,
        @Query("format") format: String = "json",
    ): Response<TrackModel>

    @GET("/2.0/")
    suspend fun getInfo(
        @Query("method") method: String = "tag.getinfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("tag") tag: String,
        @Query("format") format: String = "json",
    ): Response<InfoModel>


    @GET("/2.0/")
    suspend fun getAlbumInfo(
        @Query("method") method: String = "album.getinfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("album") album: String,
        @Query("format") format: String = "json",
    ): Response<AlbumInfoModel>

    @GET("/2.0/")
    suspend fun getAlbumTag(
        @Query("method") method: String = "album.gettoptags",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("album") album: String,
        @Query("format") format: String = "json",
    ): Response<AlbumTagModel>

    @GET("/2.0/")
    suspend fun getArtistInfo(
        @Query("method") method: String = "artist.getinfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("format") format: String = "json",
    ): Response<ArtistInfoModel>

    @GET("/2.0/")
    suspend fun getArtistTag(
        @Query("method") method: String = "artist.gettoptags",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("format") format: String = "json",
    ): Response<ArtistTagModel>


    @GET("/2.0/")
    suspend fun getArtistTracks(
        @Query("method") method: String = "artist.gettoptracks",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("format") format: String = "json",
    ): Response<ArtistTracksModel>

    @GET("/2.0/")
    suspend fun getArtistAlbum(
        @Query("method") method: String = "artist.gettopalbums",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("artist") artist: String,
        @Query("format") format: String = "json",
    ): Response<ArtistAlbumModel>


}