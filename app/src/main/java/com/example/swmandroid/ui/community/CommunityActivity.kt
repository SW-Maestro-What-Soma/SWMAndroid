package com.example.swmandroid.ui.community

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityCommunityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityActivity : BaseActivity<ActivityCommunityBinding>({ ActivityCommunityBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private val buttonList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initList()
        initView()
        buttonClick()
    }

    private fun initList() = with(binding) {
        buttonList.apply {
            add(fullCommunityButton)
            add(jobpostingCommunityButton)
            add(jobreviewCommunityButton)
            add(studyCommunityButton)
            add(questionCommunityButton)
        }
    }

    private fun initView() {
        when (communityViewModel.categoryData) {
            "채용공고" -> selectedEvent(1)
            "채용후기" -> selectedEvent(2)
            "스터디" -> selectedEvent(3)
            "질문" -> selectedEvent(4)
            else -> selectedEvent(0)
        }
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener {
            finish()
        }

        fullCommunityButton.setOnClickListener {
            setFullView()
        }

        jobpostingCommunityButton.setOnClickListener {
            communityViewModel.setCategory("채용공고")

            setJobPostingView()
        }

        jobreviewCommunityButton.setOnClickListener {
            communityViewModel.setCategory("채용후기")

            setJobReviewView()
        }

        studyCommunityButton.setOnClickListener {
            communityViewModel.setCategory("스터디")

            setStudyView()
        }

        questionCommunityButton.setOnClickListener {
            communityViewModel.setCategory("질문")

            setQuestionView()
        }
    }

    private fun setFullView() {
        selectedEvent(0)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

        if (nowFragment is SubCommunityFragment) {
            nowFragment.moveFullCommunityFragment()
        }
    }

    private fun setJobPostingView() {
        selectedEvent(1)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

        if (nowFragment is SubCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
        if (nowFragment is FullCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
    }

    private fun setJobReviewView() {
        selectedEvent(2)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

        if (nowFragment is SubCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
        if (nowFragment is FullCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
    }

    private fun setStudyView() {
        selectedEvent(3)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

        if (nowFragment is SubCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
        if (nowFragment is FullCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
    }

    private fun setQuestionView() {
        selectedEvent(4)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

        if (nowFragment is SubCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
        if (nowFragment is FullCommunityFragment) {
            nowFragment.moveSubCommunityFragment()
        }
    }

    private fun selectedEvent(idx: Int) {
        for (i in 0 until buttonList.size) {
            if (i == idx) {
                buttonList[i].apply {
                    setBackgroundResource(R.drawable.selected_tab)
                    setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
                }
            } else {
                buttonList[i].apply {
                    setBackgroundResource(R.drawable.unselected_tab)
                    setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
                }
            }
        }
    }

    fun setTopCategoryPosition(position: Int) {
        selectedEvent(position)
    }

    fun topButtonClickBlock(check : Boolean) {
        buttonList.forEach {
            it.isClickable = check
        }
    }

}