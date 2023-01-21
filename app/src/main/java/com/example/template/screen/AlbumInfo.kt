package com.example.template.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.template.R
import com.example.template.data.albumModel.AlbumInfoModel
import com.example.template.data.mainModel.TagModel
import com.example.template.navigation.Screen
import com.example.template.utils.Constants
import com.example.template.utils.albumScreenStates.AlbumInfoState
import com.example.template.viewModel.AlbumViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumInfo(navHostController: NavHostController, videModel: AlbumViewModel = hiltViewModel()) {
    val getAlbumInfo =
        videModel.getInfo(album = Constants.album, artist = Constants.artist).collectAsState().value
    Scaffold() {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (getAlbumInfo) {
                AlbumInfoState.Empty -> {}
                is AlbumInfoState.Error -> {}
                AlbumInfoState.Loading -> {}
                is AlbumInfoState.Success -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(0.7F)) {
                            IconButton(onClick = { navHostController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }

                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getAlbumInfo.data.album.image[4].text)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = getAlbumInfo.data.album.name,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Brush.verticalGradient(
                                                0F to Color.Transparent,
                                                .5F to Color.Black.copy(alpha = 0.5F),
                                                1F to Color.Black.copy(alpha = 0.8F)
                                            )
                                        )
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 24.dp
                                        ),
                                    color = Color.White
                                )
                                Text(
                                    text = getAlbumInfo.data.album.artist,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Brush.verticalGradient(
                                                0F to Color.Transparent,
                                                .5F to Color.Black.copy(alpha = 0.5F),
                                                1F to Color.Black.copy(alpha = 0.8F)
                                            )
                                        )
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 24.dp
                                        ),
                                    color = Color.White
                                )
                            }

                        }
                        val listState = rememberLazyListState()
                        Box(modifier = Modifier.weight(1.3F).padding(10.dp)) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                LazyRow(
                                    state = listState,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .fillMaxWidth(),
                                    contentPadding = PaddingValues(10.dp),
                                    content = {
                                        items(getAlbumInfo.data.album.tags.tag) {
                                            GenreAlbumTile(modifier = Modifier, tagModel =it , navHostController =navHostController )
                                        }
                                    })
                                 Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = getAlbumInfo.data.album.wiki.summary,
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.bodyMedium

                                )
                            }

                        }
                    }

                }


            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreAlbumTile(
    modifier: Modifier.Companion,
    tagModel:AlbumInfoModel.Album.Tags.Tag,
    navHostController: NavHostController
) {
    var selected by remember {
        mutableStateOf(false)
    }
    FilterChip(
        selected = selected,
        modifier = modifier
            .padding(end = 5.dp)
            .height(30.dp)
            .width(130.dp),

        colors = FilterChipDefaults.elevatedFilterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = {
            Constants.tag=tagModel.name
            navHostController.navigate("${Screen.TagInfoScreen.route}/${tagModel.name}") {
                launchSingleTop = true
            }},
        label = {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    tagModel.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )
}