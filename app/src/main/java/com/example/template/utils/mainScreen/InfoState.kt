package com.example.template.utils.mainScreen

import com.example.template.data.mainModel.InfoModel

sealed class InfoState{
    object Empty : InfoState()
    object Loading : InfoState()
    data class Success(var data: InfoModel) : InfoState()
    data class Error(var error: String) : InfoState()
}
