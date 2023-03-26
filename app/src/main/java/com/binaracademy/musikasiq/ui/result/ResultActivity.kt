package com.binaracademy.musikasiq.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
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

	private var resultAdapter: ResultAdapter = ResultAdapter(ArrayList())
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityResultBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// savedInstanceState.getString()
		binding.shimmerViewContainer.startShimmer()
		setupRecyclerView()
		setupAction()

		lifecycleScope.launch {
			delay(2000L)
			binding.shimmerViewContainer.stopShimmer()
			binding.shimmerViewContainer.visibility = View.GONE
			resultAdapter.updateResult(ResultsData.listData)
		}
	}
	
	private fun setupRecyclerView() {
		binding.rvListMostPopular.apply {
			adapter = resultAdapter
			layoutManager = LinearLayoutManager(this@ResultActivity)
		}
	}
	
	private fun setupAction() {
		binding.imgBack.setOnClickListener {
			finish()
		}
	}
}