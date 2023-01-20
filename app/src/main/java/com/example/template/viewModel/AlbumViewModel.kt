package com.example.template.viewModel

import com.example.template.utils.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.remote.ApiService
import com.example.template.utils.albumScreenStates.AlbumInfoState
import com.example.template.utils.albumScreenStates.AlbumTagState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    val apiService: ApiService,
    val dispatcherProvider: DispatcherProvider
):ViewModel() {
    private val _getAlbumInfoState = MutableStateFlow<AlbumInfoState>(AlbumInfoState.Empty)
    private val getAlbumInfoState = _getAlbumInfoState.asStateFlow()
    private  val _getAlbumTagState=MutableStateFlow<AlbumTagState>(AlbumTagState.Empty)
    private val getAlbumTagState=_getAlbumTagState.asStateFlow()




    suspend fun getAlbumTag(album :String,artist:String): StateFlow<AlbumTagState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                _getAlbumTagState.value =AlbumTagState.Loading
                var response = apiService.getAlbumTag(album = album, artist =artist )
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it.toptags.tag
                            _getAlbumTagState.value =AlbumTagState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                       _getAlbumTagState.value =AlbumTagState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                        _getAlbumTagState.value =   AlbumTagState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                _getAlbumTagState.value =   AlbumTagState.Error(e.localizedMessage)
            }

        }

        return getAlbumTagState
    }


    suspend fun getInfo(album :String,artist:String): StateFlow<AlbumInfoState> {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
               _getAlbumInfoState.value =AlbumInfoState.Loading
                var response = apiService.getAlbumInfo(album =album , artist =artist )
                if (response.isSuccessful) {
                    response.body().let {
                        it?.let {
                            var list = it
                           _getAlbumInfoState.value =AlbumInfoState.Success(list)
                        }
                    }
                } else {
                    try {
                        var jObjError = JSONObject(response.errorBody()!!.string())
                       _getAlbumInfoState.value =
                           AlbumInfoState.Error(jObjError.getJSONObject("error").toString())
                    } catch (e: Exception) {
                       _getAlbumInfoState.value =AlbumInfoState.Error(e.localizedMessage)
                    }
                }
            } catch (e: Exception) {
               _getAlbumInfoState.value =AlbumInfoState.Error(e.localizedMessage)
            }
        }

        return getAlbumInfoState
    }
}

