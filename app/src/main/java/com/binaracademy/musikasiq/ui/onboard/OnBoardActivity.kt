package com.binaracademy.musikasiq.ui.onboard

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.databinding.ActivityOnboardBinding
import com.binaracademy.musikasiq.ui.login.LoginActivity
import com.binaracademy.musikasiq.utils.helpers.intentTo

class OnBoardActivity : AppCompatActivity() {
	private val binding: ActivityOnboardBinding by lazy {
		ActivityOnboardBinding.inflate(layoutInflater)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		// Hide the status bar
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			window.insetsController?.hide(WindowInsets.Type.statusBars())
		} else {
			window.setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN
			)
		}
		
		binding.videoView.setMediaController(null)
		binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.onboarding))
		binding.videoView.requestFocus()
		binding.videoView.start()
		
		binding.apply {
			btnGetStarted.setOnClickListener {
				intentTo(LoginActivity::class.java)
			}
		}
	}
}