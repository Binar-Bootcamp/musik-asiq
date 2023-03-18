package com.binaracademy.musikasiq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.musikasiq.databinding.FragmentHomeBinding
import com.binaracademy.musikasiq.utils.hideSoftKeyboard


class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding

	private val popularAdapter = MostPopularAdapter(listOf("Budi", "Fitri", "Adi", "Fina"))

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.root.setOnClickListener { it.hideSoftKeyboard() }
		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		binding.rvListMostPopular.apply {
			adapter = popularAdapter
			layoutManager = GridLayoutManager(activity, 2)
		}
	}
}