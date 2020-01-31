package com.devproject.miguelfagundez.youtubeplayer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devproject.miguelfagundez.youtubeplayer.R
import com.devproject.miguelfagundez.youtubeplayer.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

/****************************************
 * MainActivity class.
 * Our first view
 * @author Miguel Fagundez
 * @since 01/31/2020
 * @version 1.0
 **************************************/
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG_MAIN_ACTIVITY", "onCreate..")

        var searchCriteria = Constants.SEARCH_CRITERIA
        btn_search_youtube.setOnClickListener {view ->
            et_search_data?.text.toString().let {criteria ->
                if(!criteria.isEmpty()){
                    searchCriteria = criteria
                }else{
                    Toast.makeText(this,"Edit Text empty..",Toast.LENGTH_SHORT).show()

                }
            }
            Log.d("TAG_SEARCH_CRITERIA","$searchCriteria")
            val intent = Intent(this,YoutubeListActivity::class.java)
            intent.putExtra(Constants.INTENT_PLAYER_ACTIVITY,searchCriteria)
            startActivity(intent)
        }

    }
    //***************************************************************************************
        /*btn_search_youtube.setOnClickListener {

            var youtube_url = Constants.VIDEO_ID_AVENGER
            /*val youtube_url = et_search_data?.text.toString()

            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra(Constants.CALL_PLAYER_ACTIVITY,youtube_url)
            startActivity(intent)*/

            et_search_data?.text.toString()?.let {videoID ->
                if(!videoID.isEmpty()) {
                    youtube_url = videoID
                    Log.d("TAG_BTN", "Inside if")
                }
            }
            val youtubeIntent = YouTubeStandalonePlayer.createVideoIntent(
                this,
                Config.YOUTUBE_API_KEY,
                youtube_url)
            startActivity(youtubeIntent)

        }*/
    //***************************************************************************************

}
