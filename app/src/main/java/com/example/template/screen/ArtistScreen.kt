package com.example.template.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.template.R
import com.example.template.data.albumModel.AlbumInfoModel
import com.example.template.data.artistModel.ArtistAlbumModel
import com.example.template.data.artistModel.ArtistInfoModel
import com.example.template.data.artistModel.ArtistTracksModel
import com.example.template.data.mainModel.AlbumModel
import com.example.template.data.mainModel.ArtistModel
import com.example.template.data.mainModel.TrackModel
import com.example.template.navigation.Screen
import com.example.template.utils.Constants
import com.example.template.utils.artistScreenState.ArtistInfoState
import com.example.template.utils.artistScreenState.ArtistState
import com.example.template.utils.mainScreen.AppState
import com.example.template.viewModel.ArtistViewModel
import com.example.template.viewModel.MainViewModel
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistScreen(
    navHostController: NavHostController,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    val getArtist = viewModel.getInfo(Constants.artist).collectAsState().value
    Scaffold() {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (getArtist) {
                ArtistInfoState.Empty -> {}
                is ArtistInfoState.Error -> {}
                ArtistInfoState.Loading ->{}
                is ArtistInfoState.Success -> {

                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(0.4F)) {
                            IconButton(onClick = { navHostController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }

                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getArtist.data.artist.image[4].text)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                                , verticalArrangement = Arrangement.Bottom,
                            ) {
                                Text(
                                    text = getArtist.data.artist.name,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Black
                                )
                                Row(
                                    modifier = Modifier
                                        .wrapContentHeight()
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
                                            end = 8.dp, bottom = 30.dp,
                                            top = 24.dp
                                        ),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.Bottom,
                                ) {
                                    Column {
                                        Text(
                                            text = "${getArtist.data.artist.stats.playcount.substring(0,3)}K",
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Playcount",
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "${getArtist.data.artist.stats.listeners.substring(0,3)}K",
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Followers",
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                            }

                        val listState = rememberLazyListState()
                        Box(
                            modifier = Modifier
                                .weight(0.5F)
                                .padding(12.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                LazyRow(
                                    state = listState,
                                    modifier = Modifier
                                        .wrapContentHeight()
                                        .fillMaxWidth(),
                                    contentPadding = PaddingValues(10.dp),
                                    content = {
                                        items(getArtist.data.artist.tags.tag) {
                                            GenreArtistTile(
                                                modifier = Modifier,
                                                tagModel = it,
                                                navHostController = navHostController
                                            )
                                        }
                                    })
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = getArtist.data.artist.bio.summary,
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                        }
                        Box(modifier = Modifier.weight(.6f)){
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 12.dp), horizontalAlignment = Alignment.Start) {
                                Text(text ="Top Tracks" ,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black)
                                TopTracksArtist(navController = navHostController)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text ="Top Albums" ,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black)
                                TopArtistAlbum(navController = navHostController)
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun TopArtistAlbum(navController: NavHostController, viewModel: ArtistViewModel = hiltViewModel()) {
    var getArtist = viewModel.getAlbum(Constants.tag).collectAsState().value
    when (getArtist) {
        ArtistState.Empty -> {}
        is ArtistState.Error -> {}
        ArtistState.Loading -> {}
        is ArtistState.Success -> {
            LazyRow(contentPadding = PaddingValues(end = 10.dp), content = {
                items(getArtist.data) { item ->
                    var items = item as ArtistAlbumModel.Topalbums.Album
                    ListArtistAlbumItem(artistModel = item,Modifier ,navHostController = navController)
                }
            })
        }
    }
}


@Composable
fun ListArtistAlbumItem(
    artistModel: ArtistAlbumModel.Topalbums.Album,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(modifier = modifier
        .clickable {
            Constants.album = artistModel.name
            Constants.artist = artistModel.artist.name
            navHostController.navigate(Screen.AlbumScreen.route) {
                launchSingleTop = true
            }
        }
        .padding(end = 12.dp)
        .height(150.dp)
        .width(170.dp)) {
        Card(shape = RoundedCornerShape(4.dp)) {
            Box {
                AsyncImage(
                    modifier = modifier
                        .height(150.dp)
                        .width(170.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(artistModel.image[3].text)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = artistModel.name,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                .5F to Color.Black.copy(alpha = 0.5F),
                                1F to Color.Black.copy(alpha = 0.8F)
                            )
                        )
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 24.dp),
                    color = Color.White
                )
            }
        }
    }

}


@Composable
fun TopTracksArtist(navController: NavHostController, viewModel: ArtistViewModel = hiltViewModel()) {
    var getArtistTrack = viewModel.getTracks(Constants.tag).collectAsState().value
    when (getArtistTrack) {
        ArtistState.Empty -> {}
        is ArtistState.Error -> {}
        ArtistState.Loading -> {}
        is ArtistState.Success -> {
            LazyRow(contentPadding = PaddingValues(end = 10.dp), content = {
                items(getArtistTrack.data) { item ->
                    var items = item as ArtistTracksModel.Toptracks.Track
                    ListArtistTracksItem(tracksModel = item,Modifier ,navHostController = navController)
                }
            })
        }
    }
}


@Composable
fun ListArtistTracksItem(
    tracksModel: ArtistTracksModel.Toptracks.Track,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(modifier = modifier
        .padding(end = 12.dp)
        .height(150.dp)
        .width(170.dp)
    ) {
        Card(shape = RoundedCornerShape(4.dp)) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .height(150.dp)
                        .width(170.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tracksModel.image[3].text)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = tracksModel.name,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                .5F to Color.Black.copy(alpha = 0.5F),
                                1F to Color.Black.copy(alpha = 0.8F)
                            )
                        )
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 24.dp),
                    color = Color.White
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreArtistTile(
    modifier: Modifier.Companion,
    tagModel: ArtistInfoModel.Artist.Tags.Tag,
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
            Constants.tag = tagModel.name
            navHostController.navigate("${Screen.TagInfoScreen.route}/${tagModel.name}") {
                launchSingleTop = true
            }
        },
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