package com.binaracademy.musikasiq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.musikasiq.databinding.FragmentHomeBinding
import com.binaracademy.musikasiq.utils.hideSoftKeyboard
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
	
	private var _binding: FragmentHomeBinding? = null
	
	private val binding get() = _binding!!
	
	private val popularAdapter = MostPopularAdapter()
	
	private val viewModel: HomeViewModel by viewModels()
	
	private lateinit var shimmer: ShimmerFrameLayout
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.root.setOnClickListener { it.hideSoftKeyboard() }
		binding.imgViewAvatar.load("https://ui-avatars.com/api/?name=Budi Rahmawan&size=528.svg")
		viewModel.loadPopularTracks(null)
		
		binding.shimmerViewContainer.startShimmer()
		
		viewModel.getPopularTracks().observe(viewLifecycleOwner) {
			it.onSuccess { response ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				popularAdapter.updatePopular(ArrayList(response.tracks.items))
			}
			it.onFailure {
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
			}
		}
		setupRecyclerView()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun setupRecyclerView() {
		binding.rvListMostPopular.apply {
			adapter = popularAdapter
			layoutManager = GridLayoutManager(activity, 2)
		}
	}
}