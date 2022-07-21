package com.example.swmandroid.data.repository.login.google

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class GoogleRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _userLiveData = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser> = _userLiveData

    fun getGoogleUser(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                _userLiveData.postValue(firebaseAuth.currentUser)
            } else {
                Log.d("ABC", "구글 로그인 실패 했습니다. 구글레포지토리")
            }
        }
    }
}