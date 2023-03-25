package com.binaracademy.musikasiq.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.musikasiq.databinding.ActivityResultBinding
import com.binaracademy.musikasiq.data.model.dummy.Result
import com.binaracademy.musikasiq.data.model.dummy.ResultsData
import com.binaracademy.musikasiq.ui.onboard.OnBoardActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
	private lateinit var binding: ActivityResultBinding
	
	private var list: ArrayList<Result> = arrayListOf()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityResultBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		binding.shimmerViewContainer.startShimmer()
		
		GlobalScope.launch {
			delay(2000L)
			list.addAll(ResultsData.listData)
		}
		setupRecyclerView()
		setupAction()
	}
	
	private fun setupRecyclerView() {
		binding.rvListMostPopular.apply {
			adapter = ResultAdapter(list)
			layoutManager = LinearLayoutManager(this@ResultActivity)
		}
	}
	
	private fun setupAction() {
		binding.imgBack.setOnClickListener {
			finish()
		}
	}
}