package com.example.template.utils.mainScreen


sealed class AppState() {

    object Empty : AppState()
    object Loading : AppState()
    data class Success(var data: List<*>) : AppState()
    data class Error(var error: String) : AppState()
}