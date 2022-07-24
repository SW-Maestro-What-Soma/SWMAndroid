package com.example.swmandroid.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityMainBinding
import com.example.swmandroid.ui.community.CommunityActivity
import com.example.swmandroid.ui.easylearning.EasyLearningActivity
import com.example.swmandroid.ui.fullservice.FullServiceActivity
import com.example.swmandroid.ui.mypage.MyPageActivity
import com.example.swmandroid.ui.test.TestActivity

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonClick()
    }

    private fun buttonClick(){
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
}