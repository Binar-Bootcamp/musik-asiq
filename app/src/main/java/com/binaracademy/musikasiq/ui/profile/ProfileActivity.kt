package com.binaracademy.musikasiq.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivityProfileBinding
import com.binaracademy.musikasiq.databinding.ActivityRegisterBinding

class ProfileActivity : AppCompatActivity() {
    private val binding : ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}