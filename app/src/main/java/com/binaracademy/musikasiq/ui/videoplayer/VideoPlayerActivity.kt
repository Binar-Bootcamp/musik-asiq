package com.binaracademy.musikasiq.ui.videoplayer

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.musikasiq.data.model.dummy.Video
import com.binaracademy.musikasiq.databinding.ActivityVideoPlayerBinding

@Suppress("DEPRECATION")
class VideoPlayerActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityVideoPlayerBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val data = intent.getParcelableExtra<Video>(DATA)
		
		if (data != null) {
			binding.tvTitle.text = data.name
			binding.tvArtist.text = data.artist
			binding.tvDescription.text = data.description
			
			val mediaController = MediaController(this)
			
			mediaController.setAnchorView(binding.videoView)
			mediaController.hide()
			binding.videoView.setOnPreparedListener {
				mediaController.show()
			}

			binding.videoView.setMediaController(mediaController)
			binding.videoView.setVideoURI(Uri.parse(data.videoUrl))
			binding.videoView.requestFocus()
			binding.videoView.start()
		}
		
		setupAction()
	}
	
	private fun setupAction() {
		binding.imgBack.setOnClickListener {
			finish()
		}
	}
	
	companion object {
		const val DATA = "DATA"
	}
}