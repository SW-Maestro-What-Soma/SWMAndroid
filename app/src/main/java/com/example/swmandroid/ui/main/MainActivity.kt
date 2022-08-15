package com.example.swmandroid.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityMainBinding
import com.example.swmandroid.model.book.BookItem
import com.example.swmandroid.model.jobposting.JobPostingItem
import com.example.swmandroid.ui.community.CommunityActivity
import com.example.swmandroid.ui.easylearning.EasyLearningActivity
import com.example.swmandroid.ui.fullservice.FullServiceActivity
import com.example.swmandroid.ui.main.adapter.BookAdapter
import com.example.swmandroid.ui.main.adapter.JobPostingAdapter
import com.example.swmandroid.ui.mypage.MyPageActivity
import com.example.swmandroid.ui.test.TestActivity
import com.example.swmandroid.ui.test.TestResultActivity
import kotlin.math.abs

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    private lateinit var jobPostingItemList: ArrayList<JobPostingItem>
    private lateinit var jobPostingAdapter: JobPostingAdapter
    private lateinit var jobPostingHandle: Handler
    private lateinit var jobPostingRun: Runnable

    private lateinit var bookItemList: ArrayList<BookItem>

    var totalScore = 0
    var isPlatinum = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.parseColor("#0083E1")

        initView()
        buttonClick()
        jobPostingItemSlider()
        initJobPostingItem()
        bookItemRecyclerview()
        initBookItem()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() = with(binding) {
        //TODO 닉네임 로직 구현시 변경
        nickNameTextview.text = "김시진님"

        //TODO 토탈 점수 가져오기 구현
        totalScore = 450
        if (totalScore > 0) {
            //preTestScoreTextview.text = "이전 점수 $totalScore / 500"
            //preTestResultLayout.visibility = View.VISIBLE
            preTestResultButton.visibility = View.VISIBLE
            preTestResultButton.setOnClickListener {
                moveActivity(TestResultActivity())
            }
        } else {
            //preTestResultLayout.visibility = View.GONE
            preTestResultButton.visibility = View.GONE
        }

        //TODO 플래티넘 등급 이상인지 확인하기 구현
        isPlatinum = true
        if (isPlatinum) {
            makeProblemButton.visibility = View.VISIBLE
            makeProblemButton.setOnClickListener {
                moveActivity(MakeProblemActivity())
            }
        } else {
            makeProblemButton.visibility = View.GONE
        }
    }

    private fun buttonClick() = with(binding) {
        fullServiceButton.setOnClickListener { moveActivity(FullServiceActivity()) }
        myPageButton.setOnClickListener { moveActivity(MyPageActivity()) }
        easyLearningButton.setOnClickListener { moveActivity(EasyLearningActivity()) }
        testButton.setOnClickListener { moveActivity(TestActivity()) }
        communityButton.setOnClickListener { moveActivity(CommunityActivity()) }
    }

    private fun moveActivity(goToActivity: AppCompatActivity) {
        val intent = Intent(this, goToActivity::class.java)
        startActivity(intent)
    }

    private fun jobPostingItemSlider() = with(binding) {
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

    private fun initJobPostingItem() {
        //TODO : 채용광고 서버 API를 통해 가져와야함
        jobPostingItemList.add(JobPostingItem(R.drawable.sli1))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli2))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli3))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli4))
        jobPostingItemList.add(JobPostingItem(R.drawable.sli5))
    }

    private fun bookItemRecyclerview() = with(binding) {
        bookItemList = ArrayList()
        bookRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        bookRecyclerview.adapter = BookAdapter(bookItemList) { book ->
            val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
            intent.putExtra("Book", book)
            startActivity(intent)
        }
    }

    private fun initBookItem() {
        //TODO : 추천책 리스트 API를 통해 가져와야함
        bookItemList.add(BookItem(R.drawable.book1, "이펙티브 코틀린", "좋은 이펙티브 코틀린 책입니다.", "https://naver.com"))
        bookItemList.add(BookItem(R.drawable.book2, "리팩터링", "좋은 리팩터링 책입니다.", "https://naver.com"))
        bookItemList.add(BookItem(R.drawable.book3, "클린코드", "좋은 클린코드 책입니다.", "https://naver.com"))
        bookItemList.add(BookItem(R.drawable.book4, "코틀린 쿡북", "좋은 코틀린 쿡북 책입니다.", "https://naver.com"))
        bookItemList.add(BookItem(R.drawable.book5, "코틀린을 다루는 기술", "좋은 코틀린을 다루는 기술 책입술다.", "https://naver.com"))
    }

    override fun onPause() {
        super.onPause()
        jobPostingHandle.removeCallbacks(jobPostingRun)
    }

    override fun onResume() {
        super.onResume()
        jobPostingHandle.postDelayed(jobPostingRun, 2000)
    }

}