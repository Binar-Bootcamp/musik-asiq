package com.binaracademy.musikasiq.ui.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.binaracademy.musikasiq.R

class FavoriteFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_favorite, container, false)
	}
	
}