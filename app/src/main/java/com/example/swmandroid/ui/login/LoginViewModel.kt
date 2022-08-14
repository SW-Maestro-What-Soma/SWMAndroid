package com.example.swmandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.login.login.LoginRepository
import com.example.swmandroid.model.login.UserEntity
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val googleRepository: GoogleRepository
) : ViewModel() {

    private val _googleCurrentUser = googleRepository.userLiveData
    val googleCurrentUser: LiveData<FirebaseUser> = _googleCurrentUser

    private val _kakaoCurrentUser = MutableLiveData<OAuthToken?>()
    val kakaoCurrentUser: LiveData<OAuthToken?> = _kakaoCurrentUser

    private val _signUpUserEntity = MutableLiveData<UserEntity>()
    val signUpUserEntity: LiveData<UserEntity> = _signUpUserEntity

    fun googleAddToken(token: String) {
        googleRepository.getGoogleUser(token)
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

    suspend fun getProfile() {
        loginRepository.getProfile()
    }

    suspend fun postSignUp(userEntity: UserEntity): Boolean =
        if (loginRepository.postSignUp(userEntity).code() == 200) {
            _signUpUserEntity.value = userEntity
            true
        } else {
            false
        }

}
