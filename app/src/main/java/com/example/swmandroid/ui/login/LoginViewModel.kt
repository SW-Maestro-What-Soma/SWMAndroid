package com.example.swmandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.repository.login.email.LoginRepository
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val googleRepository: GoogleRepository
) : ViewModel() {

    private val _googleCurrentUser = googleRepository.userLiveData
    val googleCurrentUser: LiveData<FirebaseUser> = _googleCurrentUser

    private val _kakaoCurrentUser = MutableLiveData<OAuthToken?>()
    val kakaoCurrentUser: LiveData<OAuthToken?> = _kakaoCurrentUser

    private val _kakaoEmail = MutableLiveData<String>()
    val kakaoEmail: LiveData<String> = _kakaoEmail

    private val _isStatusCode200 = MutableLiveData<Boolean>()
    val isStatusCode200: LiveData<Boolean> = _isStatusCode200

    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    fun googleAddToken(token: String) {
        googleRepository.getGoogleUser(token)
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

    fun kakaoSetEmail(email: String) {
        _kakaoEmail.value = email
    }

    suspend fun postSignUp(userEntity: UserEntity) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _isStatusCode200.value = false
        }) {
            val code = withContext(Dispatchers.IO) {
                loginRepository.postSignUp(userEntity).code()
            }

            _isStatusCode200.value = code == 200
        }
    }

    suspend fun postLogin(loginInfo: LoginInfo) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            _userProfile.value = null
        }) {
            val response = withContext(Dispatchers.IO) {
                loginRepository.postLogin(loginInfo)
            }

            if (response.isSuccessful) {
                val body = response.body() ?: return@launch
                _userProfile.value = body
            } else {
                _userProfile.value = null
            }
        }
    }
}