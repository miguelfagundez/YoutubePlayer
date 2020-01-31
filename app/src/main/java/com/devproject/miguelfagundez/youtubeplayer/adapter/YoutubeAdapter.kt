package com.devproject.miguelfagundez.youtubeplayer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devproject.miguelfagundez.youtubeplayer.R
import com.devproject.miguelfagundez.youtubeplayer.model.Item
import com.devproject.miguelfagundez.youtubeplayer.utils.Constants
import com.squareup.picasso.Picasso

/****************************************
 * YoutubeAdapter class.
 * Adapter class for our recycler view
 * @author Miguel Fagundez
 * @since 01/31/2020
 * @version 1.0
 **************************************/
class YoutubeAdapter (var listVideos : List<Item> ) : RecyclerView.Adapter<YoutubeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_player,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVideos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {

            tvDescription.text = listVideos[position]
                .snippet
                .description.toString()

            var channel :String = tvChannelID.text.toString()
            tvChannelID.text = channel.plus(listVideos[position]
                .snippet
                .channelId.toString())

            tvVideoTitle.text = listVideos[position]
                .snippet
                .title.toString()

            var imagePath :String = listVideos[position]
                .snippet
                .thumbnails
                .medium
                .url
                .toString()

            Picasso.get().load(imagePath).into(image)

            itemView.setOnClickListener {view ->

                val intent = Intent(Constants.INTENT_FILTER)
                intent.putExtra(Constants.INTENT_PLAYER_VIDEO_ID, listVideos[position].id.videoId.toString())
                itemView.context.sendBroadcast(intent)
            }
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_video_image)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_description)
        val tvChannelID : TextView = itemView.findViewById(R.id.tv_channel_id)
        val tvVideoTitle : TextView = itemView.findViewById(R.id.tv_video_title)
    }

}