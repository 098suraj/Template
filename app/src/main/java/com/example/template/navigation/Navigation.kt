package com.example.template.navigation

 sealed class Screen(val route:String){
     object TagScreen: Screen("TagScreen")
     object TagInfoScreen: Screen("TagInfoScreen")
     object AlbumScreen: Screen("AlbumScreen")
     object ArtistScreen: Screen("ArtistScreen")
 }