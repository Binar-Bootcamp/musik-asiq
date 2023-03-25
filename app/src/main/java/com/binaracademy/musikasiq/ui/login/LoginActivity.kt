package com.binaracademy.musikasiq.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.binaracademy.musikasiq.databinding.ActivityLoginBinding
import com.binaracademy.musikasiq.ui.main.MainActivity
import com.binaracademy.musikasiq.ui.register.RegisterActivity
import com.binaracademy.musikasiq.viewmodel.LoginRegisterViewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginRegisterViewModel by viewModels()

    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.loginResult.observe(this) {result ->
            Log.d("TAG", "onCreate: DEBUGGING user login observer")

            result.onSuccess {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            result.onFailure {
                Toast.makeText(this, "Login Error : ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLoginNext.setOnClickListener{
            binding.apply {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                viewModel.login(username, password)
            }
        }

        binding.tvRegistNow.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}