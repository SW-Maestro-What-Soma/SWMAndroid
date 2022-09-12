package com.example.swmandroid.util

import com.example.swmandroid.GlobalApplication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

fun getEmailFromDataStore(): String = runBlocking {
    GlobalApplication.getInstance().getDataStore().email.first()
}