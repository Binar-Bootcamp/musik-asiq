package com.binaracademy.musikasiq.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.binaracademy.musikasiq.data.room.User
import com.binaracademy.musikasiq.databinding.ActivityRegisterBinding
import com.binaracademy.musikasiq.ui.login.LoginActivity
import com.binaracademy.musikasiq.viewmodel.LoginRegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private val viewModel: LoginRegisterViewModel by viewModels()

    private val binding : ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.registerSuccess.observe(this) {user ->
            if (user != null){
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnRegisNext.setOnClickListener{
            val name = binding.etName.text.toString()
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val user = User(
                name = name,
                email = email,
                password = password
            )
            viewModel.register(user)
        }

        binding.tvLoginNow.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}