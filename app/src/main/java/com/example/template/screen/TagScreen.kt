package com.example.template.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.template.data.mainModel.TagModel
import com.example.template.navigation.Screen
import com.example.template.ui.theme.TemplateTheme
import com.example.template.utils.Constants
import com.example.template.utils.mainScreen.AppState
import com.example.template.viewModel.MainViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TagScreen(navHostController: NavHostController,viewModel: MainViewModel = hiltViewModel<MainViewModel>()) {
    val genreList = viewModel.getTag().collectAsState(initial = AppState.Empty).value
    val isExpanded =viewModel.getExpanded().collectAsState(initial = false).value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Text(
                text = "musicwiki",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.clickable {
                   viewModel.setExpanded()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Choose a genre to Start with",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )

            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
            when (genreList) {
                AppState.Empty -> {
                    Timber.tag("TagScreen").d("Empty")
                }

                is AppState.Error -> {

                    Timber.tag("TagScreen").d("Error")
                }

                AppState.Loading -> {
                    Timber.tag("TagScreen").d("Loading")
                }

                is AppState.Success -> {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp),
                        maxItemsInEachRow = 3,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        when (isExpanded) {
                            true -> {
                                Tiles(genreList.data, navHostController)
                            }
                           false ->{
                               val size by remember { mutableStateOf((genreList.data.size) / 2) }
                               Tiles(data = genreList.data.subList(0, (size + 1) / 2), navHostController)
                            }
                        }

                    }


                }


            }


        }


    }
}

@Composable
fun Tiles(data: List<*>, navHostController: NavHostController) {
    data.forEachIndexed { index, any ->
        var tagModel = any as TagModel.Toptags.Tag
        GenreTile(Modifier, tagModel = tagModel, navHostController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreTile(
    modifier: Modifier.Companion,
    tagModel: TagModel.Toptags.Tag,
    navHostController: NavHostController
) {
    var selected by remember {
        mutableStateOf(false)
    }
    FilterChip(
        selected = selected,
        modifier = modifier
            .padding(bottom = 10.dp)
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

@Preview(showBackground = true)
@Composable
fun Display() {
    TemplateTheme {
    }
}
