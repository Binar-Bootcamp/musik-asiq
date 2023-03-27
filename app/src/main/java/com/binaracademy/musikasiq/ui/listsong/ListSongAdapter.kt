package com.binaracademy.musikasiq.ui.listsong

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.utils.load
import java.util.*

class ListSongAdapter(private val listSong: ArrayList<TrackItem>) :
    RecyclerView.Adapter<ListSongAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback = onItemClickCallback()

    private var onFavClickCallback: OnItemClickCallback = onItemClickCallback()

    private fun onItemClickCallback() = object : OnItemClickCallback {
            override fun onItemClick(track: TrackItem) {
                // dont do anything :(
            }
        }

    interface OnItemClickCallback {
        fun onItemClick(track: TrackItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listSong[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(data)
        }

        holder.imgPhoto.load(data.artworkUrl ?: "https://i.pravatar.cc/300")
        holder.tvName.text = data.title.split("-").first()
        holder.tvDetail.text = if (data.title.split("-").size >= 2) {
            data.title.split("-")[1]
        } else "-"
        holder.imgFav.setOnClickListener {
            onFavClickCallback.onItemClick(data)
        }
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateResult(results: ArrayList<TrackItem>) {
        this.listSong.clear()
        this.listSong.addAll(results)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnFavoriteClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onFavClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_title_song)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_year_of_release)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_view_playlist_head)
        var imgFav: ImageView = itemView.findViewById(R.id.img_view_fav)
    }
}