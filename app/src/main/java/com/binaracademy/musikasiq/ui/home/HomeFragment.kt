package com.binaracademy.musikasiq.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.musikasiq.databinding.FragmentHomeBinding
import com.binaracademy.musikasiq.ui.result.ResultActivity
import com.binaracademy.musikasiq.utils.hideSoftKeyboard
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
	
	private var _binding: FragmentHomeBinding? = null
	
	private val binding get() = _binding!!
	
	private val popularAdapter = MostPopularAdapter()
	
	private val viewModel: HomeViewModel by viewModels()
	
	val name = "Ryan"
	
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
		binding.imgViewAvatar.load("https://ui-avatars.com/api/?name=${name}&size=528.svg")
		viewModel.loadPopularTracks(null)
		
		binding.shimmerViewContainer.startShimmer()
		
		viewModel.getPopularTracks().observe(viewLifecycleOwner) {
			it.onSuccess { response ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				popularAdapter.updatePopular(ArrayList(response.tracks.items))
			}
		}
		setupRecyclerView()
		setUpAction()
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
	
	private fun setUpAction() {
		binding.searchField.setOnEditorActionListener { _, actionId, _ ->
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				// Perform search action
				onSearch(binding.searchField.text.toString())
				true
			} else {
				false
			}
		}
	}
	
	private fun onSearch(query: String) {
		val intent = Intent(this.requireContext(), ResultActivity::class.java)
		intent.putExtra(KEYWORD, query)
		startActivity(intent)
	}
	
	companion object {
		const val KEYWORD = "KEYWORD"
	}
	
}