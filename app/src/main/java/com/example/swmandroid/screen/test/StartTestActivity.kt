package com.example.swmandroid.screen.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swmandroid.databinding.ActivityStartTestBinding
import com.example.swmandroid.screen.test.adapter.TestProblemViewPagerAdapter

class StartTestActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartTestBinding.inflate(layoutInflater)

        binding.viewPager.adapter = TestProblemViewPagerAdapter(supportFragmentManager, lifecycle)

        setContentView(binding.root)
    }
}