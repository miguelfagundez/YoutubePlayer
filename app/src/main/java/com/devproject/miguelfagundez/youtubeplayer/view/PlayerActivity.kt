package com.devproject.miguelfagundez.youtubeplayer.view

import android.os.Bundle
import android.util.Log
import com.devproject.miguelfagundez.youtubeplayer.R
import com.devproject.miguelfagundez.youtubeplayer.utils.Config
import com.devproject.miguelfagundez.youtubeplayer.utils.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_player2.*

/****************************************
 * PlayerActivity class.
 * Connect with youtube to display video id
 * @author Miguel Fagundez
 * @since 01/31/2020
 * @version 1.0
 **************************************/
class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player2)

        intent?.getStringExtra(Constants.INTENT_PLAYER_ACTIVITY)?.let { data ->
            Log.d("TAG_PLAYER_ACTIVITY","$data")
            id = data
            youtube_player_view.initialize(Config.YOUTUBE_API_KEY,this)
        }
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        Log.d("TAG_PLAYER_ACTIVITY","Initialization is Done!!")
        p1?.loadVideo(id)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.d("TAG_PLAYER_ACTIVITY","Failed to initialized")
    }
}
