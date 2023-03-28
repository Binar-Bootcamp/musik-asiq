package com.binaracademy.musikasiq.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.databinding.FragmentListSongBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.ui.profile.ProfileActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.viewmodel.FavoriteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    private var _binding: FragmentListSongBinding? = null

    private val binding get() = _binding!!

    private var favoriteAdapter: FavoriteAdapter = FavoriteAdapter(ArrayList())

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setupObserver()
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
        favoriteAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClick(track: TrackItemAbstract) {
                val intent = Intent(
                    requireContext(),
                    MediaPlayerActivity::class.java
                )
                intent.putExtra(HomeFragment.TRACK_ITEM, track)
                startActivity(intent)
            }
        })

        favoriteAdapter.setOnFavoriteClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClick(track: TrackItemAbstract) {
                viewModel.deleteFavorite(track)
                Toast.makeText(context, "Success remove favorite", Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvListMostPopular.apply {
            adapter = favoriteAdapter
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
    }

    private fun setupObserver() {

        lifecycleScope.launch {
            viewModel.loadFavorites(viewLifecycleOwner)
            delay(3000L)
            viewModel.getFavorites().observe(viewLifecycleOwner ){
                renderUIBasedOnFavorite(it)
            }
        }
    }

    private fun renderUIBasedOnFavorite(res: List<TrackItemAbstract?>) {
        Log.d("DEBUG ----- ", "renderUIBasedOnFavorite: $res")
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
        favoriteAdapter.updateResult(ArrayList(res.filterNotNull()))

        val success = arrayListOf<TrackItemAbstract>()
        success.addAll(ArrayList(res.filterNotNull()))
        if (success.isEmpty()) {
            binding.tvFavoriteErr.visibility = View.VISIBLE
        } else {
            binding.tvFavoriteErr.visibility = View.INVISIBLE
        }
    }

}