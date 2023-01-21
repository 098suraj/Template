package com.example.template.viewModel

import com.example.template.utils.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.remote.ApiService
import com.example.template.repository.ArtistRepository
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
    private val artistRepository: ArtistRepository,
    private val dispatcherProvider: DispatcherProvider
):ViewModel (){
    private val _getTagState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getTagState = _getTagState.asStateFlow()
    private val _getAlbumState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getAlbumState = _getAlbumState.asStateFlow()
    private val _getTrackState = MutableStateFlow<ArtistState>(ArtistState.Empty)
    private val getTracksState = _getTrackState.asStateFlow()
    private val _getArtistInfoState = MutableStateFlow<ArtistInfoState>(ArtistInfoState.Empty)
    private val getArtistInfoState = _getArtistInfoState.asStateFlow()

     fun getTag(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
            _getTagState.value=artistRepository.getTag(artist).value
        }

        return getTagState
    }


     fun getAlbum(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
          _getAlbumState.value=artistRepository.getAlbum(artist).value
        }

        return getAlbumState
    }


     fun getTracks(artist:String): StateFlow<ArtistState> {
        viewModelScope.launch(dispatcherProvider.io) {
            _getTrackState.value=artistRepository.getTracks(artist).value
        }

        return getTracksState
    }


     fun getInfo(artist: String): StateFlow<ArtistInfoState> {
        viewModelScope.launch(dispatcherProvider.io) {
            _getArtistInfoState.value=artistRepository.getInfo(artist).value
        }

        return getArtistInfoState
    }




}