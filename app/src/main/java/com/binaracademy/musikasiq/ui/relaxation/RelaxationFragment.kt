package com.binaracademy.musikasiq.ui.relaxation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.binaracademy.musikasiq.databinding.FragmentRelaxationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class RelaxationFragment : Fragment() {
	private var _binding: FragmentRelaxationBinding? = null
	
	private val binding get() = _binding!!
	
	private lateinit var tabLayoutMediator: TabLayoutMediator
	
	private val tabList = mutableListOf("Music", "Video")
	private val fragmentList = mutableListOf<Fragment>()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentRelaxationBinding.inflate(inflater, container, false)
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		initData()
		initView()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun initData() {
		fragmentList.add(MusicFragment())
		fragmentList.add(VideoFragment())
	}
	
	private fun initView() {
		val viewPager = binding.viewPager
		val tabs = binding.tabs
		viewPager.offscreenPageLimit = 2
		viewPager.adapter = TabLayoutAdapter(this.requireActivity(), fragmentList)
		viewPager.isUserInputEnabled = false
		
		tabLayoutMediator = TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
			tab.text = tabList[position]
		}
		
		tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab) {
				tabs.setSelectedTabIndicatorColor(Color.parseColor("#7673FF"))
			}
			
			override fun onTabUnselected(tab: TabLayout.Tab) {}
			
			override fun onTabReselected(tab: TabLayout.Tab) {}
		})
		
		tabLayoutMediator.attach()
		
		tabs.tabRippleColor = null
	}
	
	class TabLayoutAdapter(
		fragmentActivity: FragmentActivity,
		private val fragmentList: MutableList<Fragment>
	) :
		FragmentStateAdapter(fragmentActivity) {
		override fun getItemCount(): Int {
			return fragmentList.size
		}
		
		override fun createFragment(position: Int): Fragment {
			return fragmentList[position]
		}
	}
}