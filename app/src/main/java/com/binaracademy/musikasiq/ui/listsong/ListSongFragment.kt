package com.binaracademy.musikasiq.ui.listsong

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.room.Favorite
import com.binaracademy.musikasiq.databinding.FragmentListSongBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.ui.profile.ProfileActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.viewmodel.ListSongViewModel

class ListSongFragment : Fragment() {
	private var _binding: FragmentListSongBinding? = null
	
	private val binding get() = _binding!!
	
	private var listAdapter: ListSongAdapter = ListSongAdapter(ArrayList())

	private val viewModel: ListSongViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentListSongBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupObserver()
		initData()
		setupRecyclerView()
		setupAction()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun setupAction() {
		binding.imgViewAvatar.setOnClickListener {
			val intent = Intent(this.requireContext(), ProfileActivity::class.java)
			startActivity(intent)
		}
	}
	
	private fun setupRecyclerView() {
		listAdapter.setOnItemClickCallback(object : ListSongAdapter.OnItemClickCallback {
			override fun onItemClick(track: TrackItem) {
				val intent = Intent(
					requireContext(),
					MediaPlayerActivity::class.java
				)
				intent.putExtra(HomeFragment.TRACK_ITEM, track)
				startActivity(intent)
			}
		})
		listAdapter.setOnFavoriteClickCallback(object : ListSongAdapter.OnItemClickCallback {
			override fun onItemClick(track: TrackItem) {
				val favorite = Favorite(
					track = track
				)
				viewModel.clickFavorite(favorite)
			}
		})
		binding.rvListMostPopular.apply {
			adapter = listAdapter
			layoutManager = LinearLayoutManager(requireActivity())
		}
	}
	
	private fun initData() {
		val sharedPreferences = SharedPreferencesManager(requireContext(), Constants.APP_TABLE)
		
		val name = sharedPreferences.getString(Constants.NAME_SP_KEY, "Guest")
		val email = sharedPreferences.getString(Constants.EMAIL_SP_KEY, "root@example.com")
		
		binding.tvName.text = name
		binding.tvUsername.text = email
		
		binding.imgViewAvatar.load(
			"https://ui-avatars.com/api/?name=$name&size=528.svg"
		)
		binding.shimmerViewContainer.startShimmer()
		viewModel.loadTracks()
	}

	private fun setupObserver() {
		viewModel.getTracks().observe(viewLifecycleOwner){
			it.onSuccess { response ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				listAdapter.updateResult(ArrayList(response.tracks.items))
			}

			it.onFailure {err ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				binding.tvFavoriteErr.visibility = View.VISIBLE
				Toast.makeText(context, "Error to fetch: ${err.message}", Toast.LENGTH_SHORT).show()
			}
		}

		viewModel.getFavorite().observe(viewLifecycleOwner) {
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