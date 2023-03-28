package com.binaracademy.musikasiq.ui.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.data.model.TrackItemAbstract
import com.binaracademy.musikasiq.data.model.TrackItemOffline
import com.binaracademy.musikasiq.databinding.ActivityMediaPlayerBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.utils.showSnackbar
import com.binaracademy.musikasiq.viewmodel.MediaPlayerViewModel
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class MediaPlayerActivity : AppCompatActivity() {
	private val binding: ActivityMediaPlayerBinding by lazy {
		ActivityMediaPlayerBinding.inflate(layoutInflater)
	}
	
	private lateinit var runnable: Runnable
	
	private var handler = Handler()
	
	private lateinit var mediaPlayer: MediaPlayer
	
	private val viewModel: MediaPlayerViewModel by viewModels()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		when (val track: TrackItemAbstract? = intent.getParcelableExtra(HomeFragment.TRACK_ITEM)) {
			is TrackItem -> {
				viewModel.loadTrackMetaData(track.id.toString())
				
				viewModel.getUrlToPlay().observe(this) {
					it.onSuccess { meta ->
						binding.ivSong.load(meta.artworkUrl)
						binding.songTitle.text = meta.title
						mediaPlayer = MediaPlayer().apply {
							setDataSource(meta.audio.first().url)
							prepare()
							start()
						}
						
						setUpAction()
					}
					
					it.onFailure { error ->
						binding.root.showSnackbar(error.message.toString())
					}
				}
			}
			is TrackItemOffline -> {
				Glide.with(binding.ivSong.context)
					.load(track.thumbnail?.toInt())
					.into(binding.ivSong)
				binding.songTitle.text = track.title
				mediaPlayer = MediaPlayer.create(this, track.songResId)
				setUpAction()
			}
			else -> Log.e("ERROR", "Received data not supported")
		}
	}
	
	private fun setUpAction() {
		mediaPlayer.setOnPreparedListener {
			val totTime = formatDuration(mediaPlayer.duration.toLong())
			binding.apply {
				totalTime.text = totTime
				seekBar.max = mediaPlayer.duration
				mediaPlayer.start()
				fab.setImageResource(R.drawable.ic_pause)
			}
		}
		
		binding.apply {
			fab.setOnClickListener {
				if (!mediaPlayer.isPlaying) {
					mediaPlayer.start()
					binding.fab.setImageResource(R.drawable.ic_pause)
				} else {
					mediaPlayer.pause()
					binding.fab.setImageResource(R.drawable.ic_play)
				}
			}
			
			seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
				override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
					if (changed) {
						mediaPlayer.seekTo(position)
					}
				}
				
				override fun onStartTrackingTouch(p0: SeekBar?) {
				}
				
				override fun onStopTrackingTouch(p0: SeekBar?) {
				}
			})
			
			btnBack.setOnClickListener {
				mediaPlayer.stop()
				finish()
			}
		}
		
		runnable = Runnable {
			binding.seekBar.progress = mediaPlayer.currentPosition
			binding.currentTime.text = formatDuration(mediaPlayer.currentPosition.toLong())
			handler.postDelayed(runnable, 1000)
		}
		handler.postDelayed(runnable, 1000)
		
		mediaPlayer.setOnCompletionListener {
			binding.apply {
				seekBar.progress = 0
				fab.setImageResource(R.drawable.ic_play)
			}
		}
	}
	
	private fun formatDuration(duration: Long): String {
		val seconds = duration / 1000 % 60
		val minutes = duration / 1000 / 60
		return String.format("%d:%02d", minutes, seconds)
	}
}