package com.example.template.utils.artistScreenState

import com.example.template.data.artistModel.ArtistInfoModel

sealed interface ArtistInfoState{
    object Empty : ArtistInfoState
    object Loading : ArtistInfoState
    data class Success(var data: ArtistInfoModel) : ArtistInfoState
    data class Error(var error: String) : ArtistInfoState
}