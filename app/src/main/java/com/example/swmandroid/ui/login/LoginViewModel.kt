package com.example.swmandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.repository.login.email.LoginRepository
import com.example.swmandroid.data.repository.login.google.GoogleRepository
import com.example.swmandroid.model.login.EmailConfirm
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.model.login.UserProfile
import com.example.swmandroid.util.Resource
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

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

    private val _isStatusCode200 = MutableLiveData<Resource<ResponseBody>>()
    val isStatusCode200: LiveData<Resource<ResponseBody>> = _isStatusCode200

    private val _userProfile = MutableLiveData<Resource<UserProfile>>()
    val userProfile: LiveData<Resource<UserProfile>> = _userProfile

    private val _certification = MutableLiveData<Resource<EmailConfirm>>()
    val certification: LiveData<Resource<EmailConfirm>> = _certification

    fun googleAddToken(token: String) {
        googleRepository.getGoogleUser(token)
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

    fun kakaoSetEmail(email: String) {
        _kakaoEmail.value = email
    }

    fun postSignUp(userEntity: UserEntity) = viewModelScope.launch {
        _isStatusCode200.postValue(Resource.Loading())

        _isStatusCode200.postValue(loginRepository.postSignUp(userEntity))
    }

    fun postLogin(loginInfo: LoginInfo) = viewModelScope.launch {
        _userProfile.postValue(Resource.Loading())

        _userProfile.postValue(loginRepository.postLogin(loginInfo))
    }

    fun postEmailConfirm(userEmail: String) = viewModelScope.launch {
        _certification.postValue(Resource.Loading())

        _certification.postValue(loginRepository.postEmailConfirm(userEmail))
    }

}