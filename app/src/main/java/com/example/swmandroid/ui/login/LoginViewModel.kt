package com.example.swmandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.data.repository.login.login.LoginRepository
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    fun googleAddToken(token: String) {
        googleRepository.getGoogleUser(token)
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

    suspend fun postSignUp(userEntity: UserEntity): Boolean = withContext(Dispatchers.IO) {
        if (loginRepository.postSignUp(userEntity).code() == 200) {
            _signUpUserEntity.postValue(userEntity)
            return@withContext true
        } else {
            return@withContext false
        }
    }

    suspend fun postLogin(loginInfo: LoginInfo): Boolean = withContext(Dispatchers.IO) {
        val response = loginRepository.postLogin(loginInfo)

        if (response.code() == 200) {
            _userProfile.postValue(response.body())
            return@withContext true
        } else {
            return@withContext false
        }
    }
}
