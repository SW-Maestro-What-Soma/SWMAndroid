package com.example.swmandroid.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityBookDetailBinding
import com.example.swmandroid.model.book.BookItem

class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>({ActivityBookDetailBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding){
        val bookItem = intent.getSerializableExtra("Book") as BookItem

        closeButton.setOnClickListener { finish() }
        bookImageview.setImageResource(bookItem.img)
        bookTitle.text = bookItem.bookTitle
        bookContent.text = bookItem.bookContent
        buyButton.setOnClickListener { Toast.makeText(this@BookDetailActivity, bookItem.bookUrl, Toast.LENGTH_SHORT).show() }
    }

}