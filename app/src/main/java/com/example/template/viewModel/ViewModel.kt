package com.example.template.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.repository.MainRepository
import com.example.template.utils.DispatcherProvider
import com.example.template.utils.mainScreen.AppState
import com.example.template.utils.mainScreen.InfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _getTagState = MutableStateFlow<AppState>(AppState.Empty)
    private val getTagState = _getTagState.asStateFlow()
    private val _getAlbumState = MutableStateFlow<AppState>(AppState.Empty)
    private val getAlbumState = _getAlbumState.asStateFlow()
    private val _getTrackState = MutableStateFlow<AppState>(AppState.Empty)
    private val getTracksState = _getTrackState.asStateFlow()
    private val _getArtistState = MutableStateFlow<AppState>(AppState.Empty)
    private val getArtistState = _getArtistState.asStateFlow()
    private val _getInfoState = MutableStateFlow<InfoState>(InfoState.Empty)
    private val getInfoState = _getInfoState.asStateFlow()


    fun getTag(): StateFlow<AppState> {
        viewModelScope.launch(dispatcherProvider.io) {
          _getTagState.value=mainRepository.getTag().value
        }
        return getTagState
    }


    fun getAlbum(): StateFlow<AppState> {
        viewModelScope.launch(dispatcherProvider.io) {
            _getAlbumState.value= mainRepository.getAlbum().value
        }
        return getAlbumState
    }


    fun getTracks(): StateFlow<AppState> {
        viewModelScope.launch(dispatcherProvider.io) {
          _getTrackState.value=mainRepository.getTracks().value
        }

        return getTracksState
    }


    fun getArtist(): StateFlow<AppState> {
        viewModelScope.launch(dispatcherProvider.io) {
          _getArtistState.value=mainRepository.getArtist().value
        }

        return getArtistState
    }


    fun getInfo(): StateFlow<InfoState> {
        viewModelScope.launch(dispatcherProvider.io) {
           _getInfoState.value=getInfoState.value
        }

        return getInfoState
    }


}