package com.binaracademy.musikasiq.data.model.dummy

import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItemOffline

object ResultsData {
	private val resultNames = arrayOf(
		"Beach",
		"Forest",
		"Rain",
	)
	
	private val resultDetails = arrayOf("Unknown", "Unknown", "Unknown")
	
	private val resThumbnail = arrayOf(
		R.drawable.thumbnail_beach,
		R.drawable.thumbnail_forest,
		R.drawable.thumbnail_rain,
	)

	private val resSongsId = arrayOf(
		R.raw.beach,
		R.raw.forest,
		R.raw.rain
	)

	val musicList: ArrayList<TrackItemOffline>
		get() {
			val list = arrayListOf<TrackItemOffline>()
			for (position in resultNames.indices) {
				val result = TrackItemOffline()
				result.title = resultNames[position]
				result.description = resultDetails[position]
				result.thumbnail = resThumbnail[position].toString()
				result.songResId = resSongsId[position]
				list.add(result)
			}
			return list
		}
}