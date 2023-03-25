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
import com.binaracademy.musikasiq.ui.main.MainActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.helpers.intentTo

@Suppress("DEPRECATION")
class OnBoardActivity : AppCompatActivity() {
	private val binding: ActivityOnboardBinding by lazy {
		ActivityOnboardBinding.inflate(layoutInflater)
	}
	
	private val sharedPreferences = SharedPreferencesManager(this, Constants.APP_TABLE)
	
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
		
		val email = sharedPreferences.getString(Constants.EMAIL_SP_KEY, "-")
		
		binding.videoView.setMediaController(null)
		binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.onboarding))
		binding.videoView.requestFocus()
		binding.videoView.start()
		
		binding.apply {
			btnGetStarted.setOnClickListener {
				if (email !== "-")
					intentTo(MainActivity::class.java)
				else
					intentTo(LoginActivity::class.java)
			}
		}
	}
}