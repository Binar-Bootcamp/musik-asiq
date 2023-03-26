package com.binaracademy.musikasiq.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.musikasiq.databinding.ActivityProfileBinding
import com.binaracademy.musikasiq.databinding.ActivityRegisterBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.login.LoginActivity
import com.binaracademy.musikasiq.utils.helpers.Constants
import com.binaracademy.musikasiq.utils.helpers.SharedPreferencesManager
import com.binaracademy.musikasiq.utils.helpers.intentTo
import com.binaracademy.musikasiq.utils.load

class ProfileActivity : AppCompatActivity() {

    private val sharedPreferences = SharedPreferencesManager(this, Constants.APP_TABLE)

    private val binding : ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getUsername = sharedPreferences.getString(Constants.NAME_SP_KEY, "Guest")
        binding.tvName.text = getUsername
        binding.tvUsername.text = getUsername

        binding.photoProfile.load(
            "https://ui-avatars.com/api/?name=$getUsername&size=528.svg"
        )

        binding.btnBack.setOnClickListener {
            intentTo(HomeFragment::class.java)
        }

        binding.btnLogout.setOnClickListener {
            sharedPreferences.remove(Constants.NAME_SP_KEY)
            intentTo(LoginActivity::class.java)
        }
    }
}