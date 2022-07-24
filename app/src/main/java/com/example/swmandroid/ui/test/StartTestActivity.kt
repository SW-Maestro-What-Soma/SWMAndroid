package com.example.swmandroid.ui.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartTestBinding
import com.example.swmandroid.ui.test.adapter.TestProblemViewPagerAdapter

class StartTestActivity : BaseActivity<ActivityStartTestBinding>({ ActivityStartTestBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = TestProblemViewPagerAdapter(supportFragmentManager, lifecycle)
    }
}