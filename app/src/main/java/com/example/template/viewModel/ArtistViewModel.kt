package com.example.template.viewModel

import com.example.template.utils.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.remote.ApiService
import com.example.template.utils.artistScreenState.ArtistInfoState
import com.example.template.utils.artistScreenState.ArtistState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel 
class ArtistViewModel @Inject constructor(
    val apiService: ApiService,
    val dispatcherProvider: DispatcherProvider
):ViewModel (){
    private val _getTagState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getTagState = _getTagState.asStateFlow()
    private val _getAlbumState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getAlbumState = _getAlbumState.asStateFlow()
    private val _getTrackState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getTracksState = _getTrackState.asStateFlow()
    private val _getArtistInfoState = MutableStateFlow<ArtistInfoState>(ArtistInfoState.Empty)
    private val getArtistInfoState = _getArtistInfoState.asStateFlow()

    suspend fun getTag(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _getTagState.value = ArtistState.Loading
                var response = apiService.getArtistTag(artist = artist)
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it.toptags.tag
                            _getTagState.value = ArtistState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                        _getTagState.value = ArtistState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                        _getTagState.value =     ArtistState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                _getTagState.value =   ArtistState.Error(e.localizedMessage)
            }

        }

        return getTagState
    }


    suspend fun getAlbum(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _getAlbumState.value = ArtistState.Loading
                var response = apiService.getArtistAlbum(artist =artist )
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it.topalbums.album
                            _getAlbumState.value = ArtistState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                        _getAlbumState.value =
                            ArtistState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                        _getAlbumState.value = ArtistState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                _getAlbumState.value = ArtistState.Error(e.localizedMessage)
            }
        }

        return getAlbumState
    }


    suspend fun getTracks(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _getTrackState.value = ArtistState.Loading
                var response = apiService.getArtistTracks(artist = artist)
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it.toptracks.track
                            _getTrackState.value = ArtistState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                        _getTrackState.value =
                            ArtistState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                        _getTrackState.value = ArtistState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                _getTrackState.value = ArtistState.Error(e.localizedMessage)
            }
        }

        return getTracksState
    }


    suspend fun getInfo(artist: String,album:String): StateFlow<ArtistInfoState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                 _getArtistInfoState.value = ArtistInfoState.Loading
                var response = apiService.getArtistInfo(artist = artist )
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it
                             _getArtistInfoState.value = ArtistInfoState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                         _getArtistInfoState.value =
                            ArtistInfoState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                         _getArtistInfoState.value = ArtistInfoState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                 _getArtistInfoState.value = ArtistInfoState.Error(e.localizedMessage)
            }
        }

        return getArtistInfoState
    }




}