package com.binaracademy.musikasiq.ui.result

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.ui.home.MostPopularAdapter
import com.binaracademy.musikasiq.utils.load
import java.util.*

class ResultAdapter(private val listResult: ArrayList<TrackItem>) :
	RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

	private var onItemClickCallback: OnItemClickCallback = object : OnItemClickCallback {
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
		val data = listResult[position]

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(data)
        }
		holder.imgPhoto.load(data.artworkUrl ?: "https://i.pravatar.cc/300")
		holder.tvName.text = data.title
		holder.tvDetail.text = data.genre
	}

	override fun getItemCount(): Int {
		return listResult.size
	}

	@SuppressLint("NotifyDataSetChanged")
	fun updateResult(results: ArrayList<TrackItem>) {
		this.listResult.clear()
		this.listResult.addAll(results)
		notifyDataSetChanged()
	}

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var tvName: TextView = itemView.findViewById(R.id.tv_title_song)
		var tvDetail: TextView = itemView.findViewById(R.id.tv_year_of_release)
		var imgPhoto: ImageView = itemView.findViewById(R.id.img_view_playlist_head)
	}
}