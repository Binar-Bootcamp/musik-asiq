package com.binaracademy.musikasiq.ui.relaxation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.dummy.Video
import com.binaracademy.musikasiq.data.model.dummy.VideosData
import com.binaracademy.musikasiq.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {
	private var _binding: FragmentVideoBinding? = null
	
	private val binding get() = _binding!!
	
	private var list: ArrayList<Video> = arrayListOf()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentVideoBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.shimmerViewContainer.startShimmer()
		
		binding.rvListMostPopular.visibility = View.GONE
		list.addAll(VideosData.listData)
		
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
			adapter = VideoAdapter(list)
			layoutManager = LinearLayoutManager(requireActivity())
			
			VideoAdapter(list).setOnItemClickCallback(object : VideoAdapter.OnItemClickCallback {
				override fun onItemClick(data: Video) {
					Toast.makeText(requireActivity(), "Kamu memilih " + data.name, Toast.LENGTH_SHORT).show()
				}
			})
		}
	}
	
}