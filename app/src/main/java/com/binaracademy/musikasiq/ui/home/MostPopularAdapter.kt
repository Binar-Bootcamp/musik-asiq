package com.binaracademy.musikasiq.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.databinding.ListItemMostPopularBinding
import com.binaracademy.musikasiq.utils.helpers.DateFormatter
import com.binaracademy.musikasiq.utils.load
import java.util.*

class MostPopularAdapter(
	private val populars: ArrayList<TrackItem> = ArrayList(),
	callback: OnItemClickCallback = object: OnItemClickCallback {
		override fun onItemClick(popular: TrackItem) {}
	}
) :
	RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {

	private var onItemClickCallback: OnItemClickCallback = callback


	inner class ViewHolder(private val binding: ListItemMostPopularBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(popular: TrackItem) {
			binding.imgViewPlaylistHead.load(popular.artworkUrl ?: "https://i.pravatar.cc/300")
			binding.tvTitleSong.text = popular.title
			binding.tvYearOfRelease.text = DateFormatter.fromISOToFormatString(popular.createdAt)
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding: ListItemMostPopularBinding =
			ListItemMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}
	
	override fun getItemCount(): Int = populars.size
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.itemView.setOnClickListener{
			onItemClickCallback.onItemClick(populars[position])
		}
		holder.bind(populars[position])
	}

	interface OnItemClickCallback {
		fun onItemClick(popular: TrackItem)
	}

	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}

	@SuppressLint("NotifyDataSetChanged")
	fun updatePopular(tracks: ArrayList<TrackItem>) {
		this.populars.clear()
		this.populars.addAll(tracks)
		notifyDataSetChanged()
	}
}