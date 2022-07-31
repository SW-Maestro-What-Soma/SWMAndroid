package com.example.swmandroid.model.book

import java.io.Serializable

data class BookItem(
    val img : Int,
    val bookTitle : String,
    val bookContent : String,
    val bookUrl : String
) : Serializable
