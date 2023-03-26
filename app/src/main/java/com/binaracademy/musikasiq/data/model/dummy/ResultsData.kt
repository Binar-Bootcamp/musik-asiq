package com.binaracademy.musikasiq.data.model.dummy

import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItemOffline

object ResultsData {
	private val resultNames = arrayOf(
		"The Shade",
		"Pluto projector",
	)
	
	private val resultDetails = arrayOf("Rex Orange Country", "Rex Orange Country")
	
	private val resultImages = arrayOf(
		"https://i.pravatar.cc/300",
		"https://i.pravatar.cc/300",
	)

	private val resThumbnail = arrayOf(
		R.drawable.ic_launcher_background,
		R.drawable.ic_launcher_background
	)

	private val resSongsId = arrayOf(
		R.raw.todh_ringtone,
		R.raw.todh_ringtone
	)

	val musicList: ArrayList<TrackItemOffline>
		get() {
			val list = arrayListOf<TrackItemOffline>()
			for (position in resultNames.indices) {
				val result = TrackItemOffline()
				result.title = resultNames[position]
				result.description = resultNames[position]
				result.thumbnail = resThumbnail[position]
				result.songResId = resSongsId[position]
				list.add(result)
			}
			return list
		}
}