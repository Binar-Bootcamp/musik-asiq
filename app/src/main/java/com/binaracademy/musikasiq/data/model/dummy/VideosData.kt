package com.binaracademy.musikasiq.data.model.dummy

import com.binaracademy.musikasiq.R

object VideosData {
	private val resultNames = arrayOf(
		"Beach",
		"Forest",
		"Rain",
	)
	
	private val resultArtists = arrayOf("Unknown", "Unknown", "Unknown")
	
	private val resultDescriptions = arrayOf("Lorem", "Lorem Ipsum", "Lorem")
	
	private val resultImages = arrayOf(
		R.drawable.thumbnail_beach,
		R.drawable.thumbnail_forest,
		R.drawable.thumbnail_rain,
	)
	
	private val resultVideoUrl = arrayOf(
		"https://i.pravatar.cc/300",
		"https://i.pravatar.cc/300",
		"https://i.pravatar.cc/300",
	)
	
	val listData: ArrayList<Video>
		get() {
			val list = arrayListOf<Video>()
			for (position in resultNames.indices) {
				val result = Video()
				result.name = resultNames[position]
				result.artist = resultArtists[position]
				result.description = resultDescriptions[position]
				result.photo = resultImages[position]
				result.videoUrl = resultVideoUrl[position]
				list.add(result)
			}
			return list
		}
}