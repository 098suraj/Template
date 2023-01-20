package com.example.template.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun SetupNavGraph(
    navController: NavHostController, screen: String
){
    NavHost(navController = navController, startDestination = screen ){
        composable(route = Screen.TagScreen.route){

        }
        composable(route = Screen.TagInfoScreen.route){

        }
        composable(route = Screen.AlbumScreen.route){

        }
        composable(route = Screen.ArtistScreen.route){

        }


    }

}