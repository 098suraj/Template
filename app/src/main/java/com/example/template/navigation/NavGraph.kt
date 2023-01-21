package com.example.template.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.template.screen.AlbumInfo
import com.example.template.screen.ArtistScreen
import com.example.template.screen.TagInfoScreen
import com.example.template.screen.TagScreen


@Composable
fun SetupNavGraph(
    navController: NavHostController, screen: String
){
    NavHost(navController = navController, startDestination = screen ){
        composable(route = Screen.TagScreen.route){
            TagScreen(navController)
        }
        composable(
            route = "${Screen.TagInfoScreen.route}/{tag}",
            arguments = listOf(navArgument("tag"){type= NavType.StringType})
        ){
            it.arguments?.getString("tag")
                ?.let { it1 -> TagInfoScreen(navHostController = navController, it1) }
        }
        composable(route = Screen.AlbumScreen.route){
           AlbumInfo(navHostController = navController)
        }
        composable(route = Screen.ArtistScreen.route){
          ArtistScreen(navHostController = navController)
        }


    }

}