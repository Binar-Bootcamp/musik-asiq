package com.binaracademy.musikasiq.ui.onboard

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.databinding.ActivityOnboardBinding
import com.binaracademy.musikasiq.ui.main.MainActivity
import com.binaracademy.musikasiq.utils.helpers.intentTo

class OnBoardActivity : AppCompatActivity() {
	private val binding: ActivityOnboardBinding by lazy {
		ActivityOnboardBinding.inflate(layoutInflater)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		binding.videoView.setMediaController(null)
		binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.onboarding))
		binding.videoView.requestFocus()
		binding.videoView.start()
		
		binding.apply {
			btnGetStarted.setOnClickListener {
				intentTo(MainActivity::class.java)
			}
		}
	}
}