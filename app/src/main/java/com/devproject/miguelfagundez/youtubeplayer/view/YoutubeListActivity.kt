package com.devproject.miguelfagundez.youtubeplayer.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.devproject.miguelfagundez.youtubeplayer.R
import com.devproject.miguelfagundez.youtubeplayer.adapter.YoutubeAdapter
import com.devproject.miguelfagundez.youtubeplayer.model.YoutubeObject
import com.devproject.miguelfagundez.youtubeplayer.utils.Config
import com.devproject.miguelfagundez.youtubeplayer.utils.Constants
import com.devproject.miguelfagundez.youtubeplayer.viewmodel.YoutubeViewModel
import kotlinx.android.synthetic.main.activity_player.*


/****************************************
 * YoutubeListActivity class.
 * Create a recycler view
 * @author Miguel Fagundez
 * @since 01/31/2020
 * @version 1.0
 **************************************/
class YoutubeListActivity : AppCompatActivity() {

    // Members: ViewModel and Observer
    private lateinit var viewModel: YoutubeViewModel
    private lateinit var observer: Observer<YoutubeObject>

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            p1?.getStringExtra(Constants.INTENT_PLAYER_VIDEO_ID)?.let {id ->
                openYoutubeVideo(id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        // registering the broadcast
        registerReceiver(receiver, IntentFilter(Constants.INTENT_FILTER))

        // Create the ViewModel object
        viewModel = ViewModelProviders.of(this).get(YoutubeViewModel::class.java)
        observer = Observer {data ->

            val adapter = YoutubeAdapter(data.items)
            youtubeRecyclerView.layoutManager = LinearLayoutManager(this)
            youtubeRecyclerView.adapter = adapter

        }

        intent?.getStringExtra(Constants.INTENT_PLAYER_ACTIVITY)?.let {criteria ->
            viewModel.searchInYoutube(
                Config.YOUTUBE_API_KEY,Config.SNIPPET_PART,criteria,Config.MAX_NUMBER_RESULTS)
                .observe(this, observer)
            Toast.makeText(this, criteria, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openYoutubeVideo(id: String) {
        val intent = Intent(this,PlayerActivity::class.java)
        intent.putExtra(Constants.INTENT_PLAYER_ACTIVITY,id)
        startActivity(intent)
        Log.d("TAG_RECEIVER","$id")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
