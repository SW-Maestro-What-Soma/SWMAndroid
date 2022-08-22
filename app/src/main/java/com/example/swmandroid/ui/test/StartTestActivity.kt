package com.example.swmandroid.ui.test

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartTestBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.test.problem.TestProblemFragment
import com.example.swmandroid.util.Resource
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class StartTestActivity : BaseActivity<ActivityStartTestBinding>({ ActivityStartTestBinding.inflate(it) }) {

    private val testViewModel by stateViewModel<TestViewModel>()

    private val fragmentList = mutableListOf<Fragment>()

    private lateinit var leftTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.progressCircular.hide()

        leftTimer = object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                testViewModel.setMilliSeconds(millisUntilFinished)
            }

            override fun onFinish() {
                Toast.makeText(this@StartTestActivity, "끝났습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@StartTestActivity, TestResultActivity::class.java)
                startActivity(intent)
            }
        }.start()

        val techStack = intent.getStringExtra("techStack")
        testViewModel.getProblemByTechStack(techStack ?: "Backend")
        testViewModel.problem.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressCircular.show()
                }
                is Resource.Success -> {
                    binding.progressCircular.hide()
                    it.data?.forEachIndexed { index, problemItem ->
                        fragmentList.add(TestProblemFragment.newInstance(index + 1, problemItem))
                    }
                    initView()
                }
                is Resource.Error -> {
                    binding.progressCircular.hide()
                    Toast.makeText(this, "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initView() = with(binding) {
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
    }

    fun moveNextProblem() {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    fun movePrevProblem() {
        binding.viewPager.currentItem = binding.viewPager.currentItem - 1
    }

    override fun onDestroy() {
        super.onDestroy()
        leftTimer.cancel()
    }

}