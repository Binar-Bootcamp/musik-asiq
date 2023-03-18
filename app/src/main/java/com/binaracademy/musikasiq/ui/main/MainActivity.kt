package com.binaracademy.musikasiq.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.binaracademy.musikasiq.R
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.binaracademy.musikasiq.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var navController: NavController
	
	private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		val navHostFragment = binding.containerFragment.getFragment<NavHostFragment>()
		navController = navHostFragment.navController
		binding.bottomNav.setupWithNavController(navController)
		
		binding.fab.setOnClickListener {
			navController.navigate(R.id.home_fragment)
		}
	}
}