package com.binaracademy.musikasiq.ui.listsong

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.databinding.FragmentListSongBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.ui.profile.ProfileActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.load

class ListSongFragment : Fragment() {
	private var _binding: FragmentListSongBinding? = null
	
	private val binding get() = _binding!!
	
	private var listAdapter: ListSongAdapter = ListSongAdapter(ArrayList())
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentListSongBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
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
	}
	
}