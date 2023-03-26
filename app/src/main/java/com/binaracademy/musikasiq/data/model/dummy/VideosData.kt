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
		"https://firebasestorage.googleapis.com/v0/b/amply-5eba6.appspot.com/o/Beach.mp4?alt=media&token=8548b408-190e-4394-9a6e-1cd68db8c5f5",
		"https://firebasestorage.googleapis.com/v0/b/amply-5eba6.appspot.com/o/River.mp4?alt=media&token=9175c957-8def-497d-bade-6076f63f4ba4",
		"https://firebasestorage.googleapis.com/v0/b/amply-5eba6.appspot.com/o/Rain.mp4?alt=media&token=fa0fdafe-93be-49ec-aac6-7fe3e8bae85b",
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