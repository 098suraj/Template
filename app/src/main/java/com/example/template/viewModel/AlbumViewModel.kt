package com.example.template.viewModel

import com.example.template.utils.DispatcherProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.remote.ApiService
import com.example.template.repository.AlbumRepository
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
    val albumRepository: AlbumRepository,
    val dispatcherProvider: DispatcherProvider
):ViewModel() {
    private val _getAlbumInfoState = MutableStateFlow<AlbumInfoState>(AlbumInfoState.Empty)
    private val getAlbumInfoState = _getAlbumInfoState.asStateFlow()
    private  val _getAlbumTagState=MutableStateFlow<AlbumTagState>(AlbumTagState.Empty)
    private val getAlbumTagState=_getAlbumTagState.asStateFlow()




     fun getAlbumTag(album :String,artist:String): StateFlow<AlbumTagState> {
        viewModelScope.launch(dispatcherProvider.io) {
          _getAlbumTagState.value=albumRepository.getAlbumTag(album,artist).value
        }

        return getAlbumTagState
    }


     fun getInfo(album :String,artist:String): StateFlow<AlbumInfoState> {
        viewModelScope.launch(dispatcherProvider.io) {
            _getAlbumInfoState.value= albumRepository.getInfo(album,artist).value

        }
        return getAlbumInfoState
    }
}

