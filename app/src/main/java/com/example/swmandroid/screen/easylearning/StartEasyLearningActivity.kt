package com.example.swmandroid.screen.easylearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swmandroid.databinding.ActivityStartEasyLearningBinding
import com.example.swmandroid.screen.easylearning.adapter.ProblemViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartEasyLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartEasyLearningBinding

    private val viewModel by viewModel<EasyLearningViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartEasyLearningBinding.inflate(layoutInflater)

        binding.viewPager.adapter = ProblemViewPagerAdapter(supportFragmentManager, lifecycle, viewModel)
        viewModel.isSwiping.observe(this) {
            binding.viewPager.isUserInputEnabled = it
        }

        setContentView(binding.root)
    }
}