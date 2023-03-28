package com.binaracademy.musikasiq.ui.relaxation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.TrackItemOffline
import com.binaracademy.musikasiq.data.model.dummy.ResultsData
import com.binaracademy.musikasiq.data.room.Favorite
import com.binaracademy.musikasiq.databinding.FragmentMusicBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.viewmodel.MusicOfflineViewModel

class MusicFragment : Fragment() {
	private var _binding: FragmentMusicBinding? = null
	
	private val binding get() = _binding!!
	
	private var list: ArrayList<TrackItemOffline> = arrayListOf()

	private val viewModel: MusicOfflineViewModel by viewModels()
	
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
		list.addAll(ResultsData.musicList)
		setupObserver()
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
		val musicAdapter = MusicAdapter(list)
		musicAdapter.setOnItemClickCallback(object : MusicAdapter.OnItemClickCallback {
			override fun onItemClick(data: TrackItemOffline) {
				Log.d("TAG", "onItemClick: UDAH DI CLICK")
				val intent = Intent(activity, MediaPlayerActivity::class.java)
				intent.putExtra(HomeFragment.TRACK_ITEM, data)
				startActivity(intent)
			}
		})
		musicAdapter.setOnFavoriteClickCallback(object : MusicAdapter.OnItemClickCallback {
			override fun onItemClick(data: TrackItemOffline) {
				val favorite = Favorite(
					offline = data
				)
				viewModel.clickFavorite(favorite)
			}
		})

		binding.rvListMostPopular.apply {
			adapter = musicAdapter
			layoutManager = LinearLayoutManager(requireActivity())
		}
	}

	private fun setupObserver() {
		viewModel.getFavorite().observe(viewLifecycleOwner) { it ->
			it.onSuccess {
				Toast.makeText(context, "Success add favorite", Toast.LENGTH_SHORT).show()
			}

			it.onFailure {error ->
				Toast.makeText(context, "Failed add favorite $error", Toast.LENGTH_SHORT).show()
				Log.e("TAG", "setupObserver: $error", )
			}
		}
	}
}