package com.binaracademy.musikasiq.ui.relaxation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.dummy.Result
import com.binaracademy.musikasiq.data.model.dummy.ResultsData
import com.binaracademy.musikasiq.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
	private var _binding: FragmentMusicBinding? = null
	
	private val binding get() = _binding!!
	
	private var list: ArrayList<Result> = arrayListOf()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentMusicBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.shimmerViewContainer.startShimmer()
		
		binding.rvListMostPopular.visibility = View.GONE
		list.addAll(ResultsData.listData)
		
		Handler(Looper.getMainLooper()).postDelayed({
			binding.rvListMostPopular.visibility = View.VISIBLE
			binding.shimmerViewContainer.stopShimmer()
			binding.shimmerViewContainer.visibility = View.GONE
		}, 2000)
		
		setupRecyclerView()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun setupRecyclerView() {
		binding.rvListMostPopular.apply {
			adapter = MusicAdapter(list)
			layoutManager = LinearLayoutManager(requireActivity())
		}
	}
	
}