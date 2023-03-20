package com.binaracademy.musikasiq.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.binaracademy.musikasiq.databinding.ActivityMainBinding
import com.binaracademy.musikasiq.databinding.ActivitySplashBinding
import com.binaracademy.musikasiq.ui.main.MainActivity

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
        Handler().postDelayed({
            val toMainActivity = Intent(this, MainActivity::class.java)
            startActivity(toMainActivity)
            finish()
        },1000)
    }
}