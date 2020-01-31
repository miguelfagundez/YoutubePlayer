package com.devproject.miguelfagundez.youtubeplayer.network

import com.devproject.miguelfagundez.youtubeplayer.model.YoutubeObject
import com.devproject.miguelfagundez.youtubeplayer.utils.Config
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****************************************
 * YoutubeRetrofit class
 * Defining the retrofit and service object
 * /builder and create them
 * @author Miguel Fagundez
 * @since 01/30/2020
 * @version 1.0
 **************************************/
class YoutubeRetrofit{

    // members
    private val service : YoutubeService
    private val retrofit : Retrofit

    // initialization block
    init {
        retrofit = createRetrofitObject()
        service = createRetrofitService(retrofit)
    }

    // Building the Youtube service
    private fun createRetrofitService(retrofit: Retrofit): YoutubeService {
        return retrofit.create(YoutubeService::class.java)
    }

    // Building the retrofit object
    private fun createRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.BASE_URL)
            .build()
    }

    // Using the retrofit service to connect with youtube
    fun searchInYoutube(api_key : String, part : String, terms : String, maxResults : Int) : Call<YoutubeObject> {
        return service.searchInYoutube(api_key,part,terms,maxResults)
    }

}