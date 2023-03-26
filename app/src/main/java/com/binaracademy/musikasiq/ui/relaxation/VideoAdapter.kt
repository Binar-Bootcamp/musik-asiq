package com.binaracademy.musikasiq.ui.relaxation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.dummy.Result
import com.binaracademy.musikasiq.data.model.dummy.Video
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class VideoAdapter(private val listVideo: ArrayList<Video>) :
	RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
	
	private lateinit var onItemClickCallback: OnItemClickCallback
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
		return ViewHolder(view)
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val data = listVideo[position]
		
		Glide.with(holder.itemView.context)
			.load(data.photo)
			.apply(RequestOptions().override(55, 55))
			.into(holder.imgPhoto)
		
		holder.tvName.text = data.name
		holder.tvArtist.text = data.artist
		holder.itemView.setOnClickListener { onItemClickCallback.onItemClick(listVideo[holder.adapterPosition]) }
	}
	
	override fun getItemCount(): Int {
		return listVideo.size
	}
	
	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}
	
	interface OnItemClickCallback {
		fun onItemClick(data: Video)
	}
	
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var tvName: TextView = itemView.findViewById(R.id.tv_title_song)
		var tvArtist: TextView = itemView.findViewById(R.id.tv_artist)
		var imgPhoto: ImageView = itemView.findViewById(R.id.img_view_playlist_head)
	}
}