package com.example.template.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.template.data.mainModel.AlbumModel
import com.example.template.data.mainModel.ArtistModel
import com.example.template.data.mainModel.TrackModel
import com.example.template.navigation.Screen
import com.example.template.utils.Constants
import com.example.template.utils.mainScreen.AppState
import com.example.template.utils.mainScreen.InfoState
import com.example.template.utils.pagerTabIndicatorOffset
import com.example.template.viewModel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagInfoScreen(
    navHostController: NavHostController,
    tag: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    var getInfo = viewModel.getInfo(tag).collectAsState().value

    Scaffold() {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Spacer(modifier = Modifier.height(40.dp))
                Box(
                    modifier = Modifier
                        .weight(0.6F)
                        .padding(10.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = tag,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        when (getInfo) {
                            is InfoState.Empty -> {}
                            is InfoState.Loading -> {}
                            is InfoState.Success -> {
                                Text(
                                    text = getInfo.data.tag.wiki.summary,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            is InfoState.Error -> {}
                        }
                    }

                }
                Box(modifier = Modifier.weight(1.4f)) {
                    TabScreen(navHostController)
                }


            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(navHostController: NavHostController) {
    val tabData = getTabList()
    val pagerState = rememberPagerState(pageCount = tabData.size)
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(tabData, pagerState, navHostController)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(tabData: List<String>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
                text = {
                    Text(text = s)
                })
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(
    tabData: List<String>,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                TopAlbum(navController = navHostController)
            }

            1 -> {
                TopArtist(navController = navHostController)
            }

            2 -> {
                TopTracks(navController = navHostController)
            }

        }

    }

}


private fun getTabList(): List<String> {
    return listOf(
        "Album",
        "Artist",
        "Tracks",

        )
}


@Composable
fun TopAlbum(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    var getAlbum = viewModel.getAlbum(Constants.tag).collectAsState().value
    when (getAlbum) {
        AppState.Empty -> {}
        is AppState.Error -> {}
        AppState.Loading -> {}
        is AppState.Success -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ), content = {
                items(getAlbum.data) { item ->
                    var items = item as AlbumModel.Albums.Album
                    ListItem(albumModel = item, navHostController = navController)
                }
            })
        }
    }
}

@Composable
fun TopTracks(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    var getTracks = viewModel.getTracks(Constants.tag).collectAsState().value
    when (getTracks) {
        AppState.Empty -> {}
        is AppState.Error -> {}
        AppState.Loading -> {}
        is AppState.Success -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ), content = {
                items(getTracks.data) { item ->
                    var items = item as TrackModel.Tracks.Track
                    ListTracksItem(tracksModel = item, navHostController = navController)
                }
            })
        }
    }
}


@Composable
fun TopArtist(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    var getArtist = viewModel.getArtist(Constants.tag).collectAsState().value
    when (getArtist) {
        AppState.Empty -> {}
        is AppState.Error -> {}
        AppState.Loading -> {}
        is AppState.Success -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ), content = {
                items(getArtist.data) { item ->
                    var items = item as ArtistModel.Topartists.Artist
                    ListArtistItem(artistModel = item, navHostController = navController)
                }
            })
        }
    }
}

@Composable
fun ListItem(
    albumModel: AlbumModel.Albums.Album,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(modifier = modifier
        .clickable {
            Constants.album = albumModel.name
            Constants.artist = albumModel.artist.name
            navHostController.navigate(Screen.AlbumScreen.route) {
                launchSingleTop = true
            }
        }
        .padding(16.dp, 8.dp)
        .height(160.dp)) {
        Card(shape = RoundedCornerShape(4.dp)) {
            Box {
                AsyncImage(
                    modifier = modifier
                        .height(160.dp)
                        .wrapContentWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(albumModel.image[3].text)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = albumModel.name,
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
fun ListArtistItem(
    artistModel: ArtistModel.Topartists.Artist,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(modifier = modifier
        .clickable {
            Constants.artist = artistModel.name
            navHostController.navigate(Screen.ArtistScreen.route) {
                launchSingleTop = true
            }
        }
        .padding(16.dp, 8.dp)
        .height(160.dp)) {
        Card(shape = RoundedCornerShape(4.dp)) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .wrapContentWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(artistModel.image[2].text)
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
fun ListTracksItem(
    tracksModel: TrackModel.Tracks.Track,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(
        modifier = modifier
            .padding(16.dp, 8.dp)
            .height(160.dp)
    ) {
        Card(shape = RoundedCornerShape(4.dp)) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .height(160.dp)
                        .wrapContentWidth(),
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
