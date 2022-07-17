package com.example.swmandroid.screen.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.swmandroid.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken

class LoginViewModel(private val app: Application, private val listener: OnSignInStartedListener) : AndroidViewModel(app) {

    private var googleAuth: FirebaseAuth = Firebase.auth

    private val _googleCurrentUser = MutableLiveData<FirebaseUser?>()

    val googleCurrentUser: LiveData<FirebaseUser?> = _googleCurrentUser

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(app, gso)

    private val _kakaoCurrentUser = MutableLiveData<OAuthToken?>()

    val kakaoCurrentUser: LiveData<OAuthToken?> = _kakaoCurrentUser

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        googleAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                _googleCurrentUser.value = googleAuth.currentUser
            } else {
                _googleCurrentUser.value = null
            }
        }
    }

    fun googleSignIn() {
        listener.onSignInStarted(googleSignInClient)
    }

    fun googleSignOut() {
        googleAuth.signOut()
    }

    fun kakaoAddToken(token: OAuthToken) {
        _kakaoCurrentUser.value = token
    }

}

class LoginViewModelFactory(
    private val application: Application,
    private val listener: OnSignInStartedListener
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(application, listener) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

interface OnSignInStartedListener {
    fun onSignInStarted(client: GoogleSignInClient?)
}

