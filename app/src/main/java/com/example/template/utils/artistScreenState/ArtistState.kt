package com.example.template.utils.artistScreenState

import com.example.template.utils.mainScreen.AppState

sealed interface ArtistState{
    object Empty : ArtistState
    object Loading : ArtistState
    data class Success(var data: List<*>) : ArtistState
    data class Error(var error: String) : ArtistState
}