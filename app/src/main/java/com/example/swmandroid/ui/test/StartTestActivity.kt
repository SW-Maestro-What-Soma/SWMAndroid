package com.example.swmandroid.ui.test

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityStartTestBinding
import com.example.swmandroid.ui.all.ViewPagerAdapter
import com.example.swmandroid.ui.test.problem.TestProblemFragment
import com.example.swmandroid.util.Resource
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class StartTestActivity : BaseActivity<ActivityStartTestBinding>({ ActivityStartTestBinding.inflate(it)}) {

    private val testViewModel by stateViewModel<TestViewModel>()
    private val fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.progressCircular.hide()

        //TODO 백엔드 문제뿐만 아니라 다른 스택 문제도 가져오도록 변경해야함
        testViewModel.getProblemByTechStack("Back-end")
        testViewModel.problem.observe(this, Observer{
            when(it){
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
                is Resource.Error ->{
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
}