package com.cm.tradetune.ui.search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.cm.tradetune.databinding.ActivitySearchBinding
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: SearchPagerAdapter
    private val resultTitles = arrayOf("User", "Indices") // Add your titles here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewPager = binding.viewPager
        viewPagerAdapter = SearchPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = resultTitles[position]
        }.attach()
        binding.searchView.requestFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Handle back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}