package com.binaracademy.musikasiq.ui.result

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.databinding.ActivityResultBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.mediaplayer.MediaPlayerActivity
import com.binaracademy.musikasiq.utils.showSnackbar
import com.binaracademy.musikasiq.viewmodel.ResultViewModel

class ResultActivity : AppCompatActivity() {
	private lateinit var binding: ActivityResultBinding

	private var resultAdapter: ResultAdapter = ResultAdapter(ArrayList())

	private val viewModel: ResultViewModel by viewModels()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityResultBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setupRecyclerView()
		setupAction()

		// get search term from home page
		val term: String = intent.getStringExtra(HomeFragment.KEYWORD) ?: "Ludovico"
		binding.searchField.setText(term)
		binding.tvTermText.text = term
		// start loading and fetch
		binding.shimmerViewContainer.startShimmer()
		viewModel.searchTrack(term)

		viewModel.getTrackResult().observe(this) {
			it.onSuccess {tracks ->
				binding.shimmerViewContainer.stopShimmer()
				binding.shimmerViewContainer.visibility = View.GONE
				resultAdapter.updateResult(ArrayList(tracks.tracks.items))
			}

			it.onFailure {
				binding.root.showSnackbar(
					message = "Failed to fetch result",
					callback = {
						viewModel.searchTrack(term)
					}
				)
			}
		}
	}
	
	private fun setupRecyclerView() {
		resultAdapter.setOnItemClickCallback(object : ResultAdapter.OnItemClickCallback {
			override fun onItemClick(track: TrackItem) {
				val intent = Intent(
					this@ResultActivity,
					MediaPlayerActivity::class.java
				)
				intent.putExtra(HomeFragment.TRACK_ITEM, track)
				startActivity(intent); this@ResultActivity.finish()
			}
		})
		binding.rvListMostPopular.apply {
			adapter = resultAdapter
			layoutManager = LinearLayoutManager(this@ResultActivity)
		}
	}
	
	private fun setupAction() {
		binding.imgBack.setOnClickListener {
			finish()
		}
	}
}