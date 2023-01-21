package com.example.template.repository

import android.util.Log
import com.example.template.remote.ApiService
import com.example.template.utils.mainScreen.AppState
import com.example.template.utils.mainScreen.InfoState
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    private val _getTagState = MutableStateFlow<AppState>(AppState.Empty)
    private val _getAlbumState = MutableStateFlow<AppState>(AppState.Empty)
    private val _getTrackState = MutableStateFlow<AppState>(AppState.Empty)
    private val _getArtistState = MutableStateFlow<AppState>(AppState.Empty)
    private val _getInfoState = MutableStateFlow<InfoState>(InfoState.Empty)


    suspend fun getTag(): MutableStateFlow<AppState> {
        try {
            _getTagState.value = AppState.Loading
            var response = apiService.getTags()
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it.toptags.tag
                        _getTagState.value = AppState.Success(list)
                    }

                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    var error = jObjError.getJSONObject("error").toString()
                    Timber.tag("GetTag").d(error)
                    _getTagState.value =
                        AppState.Error(error)
                } catch (e: Exception) {
                    Timber.tag("GetTag").d(e.localizedMessage)
                    _getTagState.value = AppState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            Timber.tag("GetTag").d(e.localizedMessage)
            _getTagState.value = AppState.Error(e.localizedMessage)
        }



        return _getTagState
    }


    suspend fun getAlbum(tag:String): MutableStateFlow<AppState> {

        try {
            _getAlbumState.value = AppState.Loading
            var response = apiService.getAlbum(tag=tag)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it.albums.album
                        _getAlbumState.value = AppState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getAlbumState.value =
                        AppState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getAlbumState.value = AppState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getAlbumState.value = AppState.Error(e.localizedMessage)
        }


        return _getAlbumState
    }


    suspend fun getTracks(tag: String): MutableStateFlow<AppState> {

        try {
            _getTrackState.value = AppState.Loading
            var response = apiService.getTracks(tag=tag)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it.tracks.track

                        _getTrackState.value = AppState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getTrackState.value =
                        AppState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getTrackState.value = AppState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getTrackState.value = AppState.Error(e.localizedMessage)
        }

        return _getTrackState
    }


    suspend fun getArtist(tag: String): MutableStateFlow<AppState> {
        try {
            _getArtistState.value = AppState.Loading
            var response = apiService.getArtist(tag=tag)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it.topartists.artist
                        _getArtistState.value = AppState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getArtistState.value =
                        AppState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getArtistState.value = AppState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getArtistState.value = AppState.Error(e.localizedMessage)
        }


        return _getArtistState
    }


    suspend fun getInfo(tag: String): MutableStateFlow<InfoState> {
        try {
            _getInfoState.value = InfoState.Loading
            var response = apiService.getInfo(tag=tag)
            if (response.isSuccessful) {
                response.body().let {
                    it?.let {
                        var list = it
                        _getInfoState.value = InfoState.Success(list)
                    }
                }
            } else {
                try {
                    var jObjError = JSONObject(response.errorBody()!!.string())
                    _getInfoState.value =
                        InfoState.Error(jObjError.getJSONObject("error").toString())
                } catch (e: Exception) {
                    _getInfoState.value = InfoState.Error(e.localizedMessage)
                }
            }
        } catch (e: Exception) {
            _getInfoState.value = InfoState.Error(e.localizedMessage)
        }


        return _getInfoState
    }


}