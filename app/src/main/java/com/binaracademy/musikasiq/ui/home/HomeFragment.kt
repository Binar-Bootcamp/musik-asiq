package com.binaracademy.musikasiq.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.databinding.FragmentHomeBinding
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.ui.profile.ProfileActivity
import com.binaracademy.musikasiq.ui.result.ResultActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.hideSoftKeyboard
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
	
	private var _binding: FragmentHomeBinding? = null
	
	private val binding get() = _binding!!
	
	private val popularAdapter = MostPopularAdapter()
	
	private val viewModel: HomeViewModel by viewModels()


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
		// load user login name to render avatar
		val sharedPreferences = SharedPreferencesManager(requireContext(), Constants.APP_TABLE)
		val name = sharedPreferences.getString(Constants.NAME_SP_KEY, "Guest")
		val email = sharedPreferences.getString(Constants.EMAIL_SP_KEY, "root@example.com")
		binding.tvEmailAvatar.text = email
		binding.tvNameAvatar.text = name
		binding.tvWelcomeName.text = context?.getString(R.string.name_with_hai, name)

		binding.imgViewAvatar.load(
			"https://ui-avatars.com/api/?name=$name&size=528.svg"
		)
		// load popular track and start loading shimmer
		viewModel.loadPopularTracks(null)
		binding.shimmerViewContainer.startShimmer()

		setupObserver()
		setupRecyclerView()
		setUpAction()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun setupObserver() {
		viewModel.getPopularTracks().observe(viewLifecycleOwner) {
			it.onSuccess { response ->
				binding.shimmerViewContainer.stopShimmer()
				binding.tvFavoriteErr.visibility = View.GONE
				binding.shimmerViewContainer.visibility = View.GONE
				popularAdapter.updatePopular(ArrayList(response.tracks.items))
			}

			it.onFailure {err ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				binding.tvFavoriteErr.visibility = View.VISIBLE
				Toast.makeText(context, "Error to fetch: ${err.message}", Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun setupRecyclerView() {
		popularAdapter.setOnItemClickCallback(object: MostPopularAdapter.OnItemClickCallback {
			override fun onItemClick(popular: TrackItem) {
				val intent = Intent(activity, MediaPlayerActivity::class.java)
				intent.putExtra(TRACK_ITEM, popular)
				startActivity(intent)
			}
		})

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

		binding.imgViewAvatar.setOnClickListener {
			val intent = Intent(this.requireContext(), ProfileActivity::class.java)
			startActivity(intent)
		}
	}
	
	private fun onSearch(query: String) {
		val intent = Intent(this.requireContext(), ResultActivity::class.java)
		intent.putExtra(KEYWORD, query)
		startActivity(intent)
	}
	
	companion object {
		const val KEYWORD = "KEYWORD"
		const val TRACK_ITEM = "TRACK_ITEM"
	}
	
}