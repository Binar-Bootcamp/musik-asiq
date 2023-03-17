package com.binaracademy.musikasiq.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}