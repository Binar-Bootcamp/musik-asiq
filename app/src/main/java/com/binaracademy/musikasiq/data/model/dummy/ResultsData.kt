package com.binaracademy.musikasiq.data.model.dummy

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
	
	val listData: ArrayList<Result>
		get() {
			val list = arrayListOf<Result>()
			for (position in resultNames.indices) {
				val result = Result()
				result.name = resultNames[position]
				result.detail = resultDetails[position]
				result.photo = resultImages[position]
				list.add(result)
			}
			return list
		}
}