package com.example.swmandroid.ui.easylearning

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartEasyLearningBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.easylearning.problem.LearningProblemFragment
import com.example.swmandroid.util.Resource
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class StartEasyLearningActivity : BaseActivity<ActivityStartEasyLearningBinding>({ ActivityStartEasyLearningBinding.inflate(it) }) {

    private val easyLearningViewModel by stateViewModel<EasyLearningViewModel>()
    private val fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.progressCircular.hide()

        //TODO 백엔드 문제뿐만아니라 다른 스택 문제도 가져오도록 변경해야함
        easyLearningViewModel.getProblemByTechStack("Back-end")
        easyLearningViewModel.problem.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressCircular.show()
                }
                is Resource.Success -> {
                    binding.progressCircular.hide()
                    it.data?.forEachIndexed { index, problemItem ->
                        fragmentList.add(LearningProblemFragment.newInstance(index + 1, problemItem))
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

    fun stopSwiping() {
        binding.viewPager.isUserInputEnabled = false
    }

    fun startSwiping() {
        binding.viewPager.isUserInputEnabled = true
    }

    fun moveNextProblem() {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    fun movePrevProblem() {
        binding.viewPager.currentItem = binding.viewPager.currentItem - 1
    }
}