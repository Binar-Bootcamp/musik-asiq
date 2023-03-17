package com.binaracademy.musikasiq.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding : ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegisNext.setOnClickListener{

        }

    }
}