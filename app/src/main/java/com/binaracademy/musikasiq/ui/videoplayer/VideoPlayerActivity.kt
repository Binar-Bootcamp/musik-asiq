package com.binaracademy.musikasiq.ui.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityVideoPlayerBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}