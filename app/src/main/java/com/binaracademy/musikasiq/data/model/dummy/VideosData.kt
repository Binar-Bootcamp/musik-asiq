package com.binaracademy.musikasiq.data.model.dummy

object VideosData {
	private val resultNames = arrayOf(
		"Atas Nama Cinta",
		"Komang",
	)
	
	private val resultArtists = arrayOf("Rossa", "Raim Laode")
	
	private val resultDescriptions = arrayOf("Lorem", "Lorem Ipsum")
	
	private val resultImages = arrayOf(
		"https://drive.google.com/drive/folders/1XLKdHeKTBESavYJM2BrII_Wyv7PDNn6r",
		"https://i.pravatar.cc/300",
	)
	
	private val resultVideoUrl = arrayOf(
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