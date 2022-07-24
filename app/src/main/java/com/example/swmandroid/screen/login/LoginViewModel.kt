package com.example.swmandroid.screen.login

import androidx.lifecycle.*
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.login.profile.ProfileRepository
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken

class LoginViewModel(
    private val loginRepository: ProfileRepository,
    private val googleRepository: GoogleRepository
) : ViewModel() {

    private val _googleCurrentUser = googleRepository.userLiveData
    val googleCurrentUser: LiveData<FirebaseUser> = _googleCurrentUser

    private val _kakaoCurrentUser = MutableLiveData<OAuthToken?>()
    val kakaoCurrentUser: LiveData<OAuthToken?> = _kakaoCurrentUser

    fun googleAddToken(token: String) {
        googleRepository.getGoogleUser(token)
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

    suspend fun getProfile() {
        loginRepository.getProfile()
    }
}
