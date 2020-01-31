package com.devproject.miguelfagundez.youtubeplayer.network

import com.devproject.miguelfagundez.youtubeplayer.model.YoutubeObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/****************************************
 * YoutubeService interface.
 * Defining the services for this youtube app
 * using retrofit 2
 * @author Miguel Fagundez
 * @since 01/30/2020
 * @version 1.0
 **************************************/
interface YoutubeService{

    // List of channels or videos
    //Search videos or channels
    @GET("/youtube/v3/search")
    fun searchInYoutube(
        @Query("key") api_key : String,
        @Query("part") part: String,
        @Query("q") terms : String,
        @Query("maxResults") maxResults : Int): Call<YoutubeObject>


}