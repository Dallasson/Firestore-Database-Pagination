package com.dz.paginationfirestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dz.paginationfirestore.R
import com.dz.paginationfirestore.databinding.ActivityMainBinding
import com.dz.paginationfirestore.viewpager.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagerAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        pagerAdapter = PagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tablayout,binding.viewPager,TabLayoutMediator.TabConfigurationStrategy(){ tab, position ->
            when(position){
                0 -> {
                     tab.text = "FireStore"
                }
                1 -> {
                     tab.text = "Database"
                }
            }
        }).attach()
    }
}