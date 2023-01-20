package com.example.template.utils.albumScreenStates

import com.example.template.data.albumModel.AlbumInfoModel


sealed interface AlbumInfoState{
    object Empty : AlbumInfoState
    object Loading : AlbumInfoState
    data class Success(var data: AlbumInfoModel) : AlbumInfoState
    data class Error(var error: String) : AlbumInfoState
}