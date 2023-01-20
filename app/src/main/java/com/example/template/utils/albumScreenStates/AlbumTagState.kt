package com.example.template.utils.albumScreenStates

import com.example.template.data.albumModel.AlbumTagModel
import com.example.template.data.artistModel.ArtistInfoModel

sealed interface AlbumTagState{
    object Empty : AlbumTagState
    object Loading : AlbumTagState
    data class Success(var data: List<AlbumTagModel.Toptags.Tag>) : AlbumTagState
    data class Error(var error: String) : AlbumTagState
}