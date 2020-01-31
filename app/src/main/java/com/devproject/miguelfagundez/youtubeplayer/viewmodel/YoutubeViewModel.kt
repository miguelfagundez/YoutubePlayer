package com.devproject.miguelfagundez.youtubeplayer.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devproject.miguelfagundez.youtubeplayer.model.YoutubeObject
import com.devproject.miguelfagundez.youtubeplayer.network.YoutubeRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/****************************************
 * YoutubeViewModel class
 * Defining the ViewModel layer
 * @author Miguel Fagundez
 * @since 01/30/2020
 * @version 1.0
 **************************************/
class YoutubeViewModel(var applicationContext: Application) : AndroidViewModel(applicationContext){

    // Mutable LiveData
    private val searchInYoutubeLiveData = MutableLiveData<YoutubeObject>()
    // Retrofit Object
    private val retrofit = YoutubeRetrofit()
    // Message TAG
    private val TAG_FAIL = "TAG_FAIL_RETROFIT"
    private val TAG_RESPO = "TAG_RESPON_RETROFIT"


    fun searchInYoutube(api_key : String, part : String, terms : String, maxResults : Int) : MutableLiveData<YoutubeObject> {
        retrofit.run {
            searchInYoutube(api_key, part, terms, maxResults)
                .enqueue(object : Callback<YoutubeObject> {
                    override fun onFailure(call: Call<YoutubeObject>, t: Throwable) {
                        Log.d(TAG_FAIL, TAG_FAIL)
                    }

                    override fun onResponse(call: Call<YoutubeObject>, response: Response<YoutubeObject>) {
                        response.body()?.let {data ->
                            searchInYoutubeLiveData.value = data
                            Log.d(TAG_RESPO, "Title[0]: ${data.pageInfo.totalResults}")
                            Log.d(TAG_RESPO, "I am here..")
                        }
                    }
                }
                )
        }
        return searchInYoutubeLiveData
    }

}