package com.binaracademy.musikasiq.ui.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.dummy.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class ResultAdapter(private val listResult: ArrayList<Result>) :
	RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.list_item_result, parent, false)
		return ViewHolder(view)
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val data = listResult[position]
		
		Glide.with(holder.itemView.context)
			.load(data.photo)
			.apply(RequestOptions().override(55, 55))
			.into(holder.imgPhoto)
		
		holder.tvName.text = data.name
		holder.tvDetail.text = data.detail
	}
	
	override fun getItemCount(): Int {
		return listResult.size
	}
	
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var tvName: TextView = itemView.findViewById(R.id.tv_title_song)
		var tvDetail: TextView = itemView.findViewById(R.id.tv_year_of_release)
		var imgPhoto: ImageView = itemView.findViewById(R.id.img_view_playlist_head)
	}
}