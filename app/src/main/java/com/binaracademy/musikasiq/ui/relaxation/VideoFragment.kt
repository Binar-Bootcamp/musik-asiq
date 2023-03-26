package com.binaracademy.musikasiq.ui.relaxation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.dummy.Video
import com.binaracademy.musikasiq.data.model.dummy.VideosData
import com.binaracademy.musikasiq.databinding.FragmentVideoBinding
import com.binaracademy.musikasiq.ui.videoplayer.VideoPlayerActivity

class VideoFragment : Fragment() {
	private var _binding: FragmentVideoBinding? = null
	
	private val binding get() = _binding!!
	
	private var list: ArrayList<Video> = arrayListOf()
	
	private lateinit var adapter: VideoAdapter
	
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
		binding.rvListMostPopular.layoutManager = LinearLayoutManager(requireActivity())
		adapter = VideoAdapter(list)
		binding.rvListMostPopular.adapter = adapter
		
		adapter.setOnItemClickCallback(object : VideoAdapter.OnItemClickCallback {
			override fun onItemClick(data: Video) {
				val intent = Intent(requireActivity(), VideoPlayerActivity::class.java)
				intent.putExtra(DATA, data)
				startActivity(intent)
			}
		})
	}
	
	companion object {
		const val DATA = "DATA"
	}
}