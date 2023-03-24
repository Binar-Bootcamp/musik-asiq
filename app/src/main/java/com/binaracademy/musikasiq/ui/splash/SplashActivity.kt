package com.binaracademy.musikasiq.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivitySplashBinding
import com.binaracademy.musikasiq.ui.main.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpAction()
    }

    private fun setUpAction() {
        GlobalScope.launch {
            delay(2000L)
            // After the delay, start the main activity
            val toMainActivity = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(toMainActivity)
            finish()
            
        }
    }
}