package com.example.swmandroid.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentHostCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityMainBinding
import com.example.swmandroid.ui.community.CommunityActivity
import com.example.swmandroid.ui.easylearning.EasyLearningActivity
import com.example.swmandroid.ui.fullservice.FullServiceActivity
import com.example.swmandroid.ui.mypage.MyPageActivity
import com.example.swmandroid.ui.test.TestActivity
import kotlin.math.abs

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    private lateinit var jobPostingItemList: ArrayList<JobPostingItem>
    private lateinit var jobPostingAdapter: JobPostingAdapter
    private lateinit var jobPostingHandle: Handler
    private lateinit var jobPostingRun: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        buttonClick()
        jobPostingItemSlider()
        jobPostingItemSliderView()
    }

    private fun buttonClick() {
        binding.fullServiceButton.setOnClickListener { moveActivity(FullServiceActivity()) }

        //Todo 닉네임 로직 구현시 변경
        binding.nickNameTextview.text = "김시진님"

        binding.myPageButton.setOnClickListener { moveActivity(MyPageActivity()) }
        binding.easyLearningButton.setOnClickListener { moveActivity(EasyLearningActivity()) }
        binding.testButton.setOnClickListener { moveActivity(TestActivity()) }
        binding.communityButton.setOnClickListener { moveActivity(CommunityActivity()) }
    }

    private fun moveActivity(goToActivity: AppCompatActivity) {
        val intent = Intent(this, goToActivity::class.java)
        startActivity(intent)
    }

    private fun jobPostingItemSlider() = with(binding){
        jobPostingItemList = ArrayList()
        jobPostingAdapter = JobPostingAdapter(jobpostingViewpager, jobPostingItemList)
        jobpostingViewpager.adapter = jobPostingAdapter
        jobpostingViewpager.clipToPadding = false
        jobpostingViewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        jobpostingViewpager.setPageTransformer(comPosPageTarn)
        jobPostingHandle = Handler(Looper.getMainLooper())
        jobPostingRun = Runnable {
            jobpostingViewpager.currentItem = jobpostingViewpager.currentItem + 1
        }
        jobpostingViewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    jobPostingHandle.removeCallbacks(jobPostingRun)
                    jobPostingHandle.postDelayed(jobPostingRun, 2000)
                }
            }
        )
    }

    private fun jobPostingItemSliderView() {
        jobPostingItemList.add(JobPostingItem(R.drawable.sli1))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli2))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli3))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli4))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli5))
    }

    override fun onPause() {
        super.onPause()
        jobPostingHandle.removeCallbacks(jobPostingRun )
    }

    override fun onResume() {
        super.onResume()
        jobPostingHandle.postDelayed(jobPostingRun, 2000)
    }

}