package com.example.template.repository

import com.example.template.remote.ApiService
import com.example.template.utils.albumScreenStates.AlbumInfoState
import com.example.template.utils.albumScreenStates.AlbumTagState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val apiService: ApiService
) {
    private val _getAlbumInfoState = MutableStateFlow<AlbumInfoState>(AlbumInfoState.Empty)
    private val getAlbumInfoState = _getAlbumInfoState.asStateFlow()
    private val _getAlbumTagState = MutableStateFlow<AlbumTagState>(AlbumTagState.Empty)
    private val getAlbumTagState = _getAlbumTagState.asStateFlow()

    suspend fun getAlbumTag(album: String, artist: String): StateFlow<AlbumTagState> {

        try {
            _getAlbumTagState.value = AlbumTagState.Loading
            var response = apiService.getAlbumTag(album = album, artist = artist)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it.toptags.tag
                        _getAlbumTagState.value = AlbumTagState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getAlbumTagState.value =
                        AlbumTagState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getAlbumTagState.value = AlbumTagState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getAlbumTagState.value = AlbumTagState.Error(e.localizedMessage)
        }



        return getAlbumTagState
    }


    suspend fun getInfo(album: String, artist: String): StateFlow<AlbumInfoState> {

        try {
            _getAlbumInfoState.value = AlbumInfoState.Loading
            var response = apiService.getAlbumInfo(album = album, artist = artist)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it
                        _getAlbumInfoState.value = AlbumInfoState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getAlbumInfoState.value =
                        AlbumInfoState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getAlbumInfoState.value = AlbumInfoState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getAlbumInfoState.value = AlbumInfoState.Error(e.localizedMessage)
        }


        return getAlbumInfoState
    }
}