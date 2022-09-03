package com.example.swmandroid.ui.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.swmandroid.BuildConfig
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentLoginBinding
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.util.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    companion object {
        const val TAG = "LOGIN_FRAGMENT"
    }

    private val loginViewModel: LoginViewModel by sharedViewModel()

    private lateinit var googleGetResult: ActivityResultLauncher<Intent>

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var kakaoCallBack: (OAuthToken?, Throwable?) -> Unit

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressCircular.hide()
        googleLoginSetting()
        kakaoLoginSetting()
        buttonClick()
    }

    private fun googleLoginSetting() {
        val app = requireNotNull(this).activity?.application
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(app!!.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(app, gso)

        googleGetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    loginViewModel.googleAddToken(account.idToken!!)
                    account.email?.let { it -> apiPostLogin(it) }
                } catch (e: ApiException) {
                    Toast.makeText(requireContext(), "구글로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun kakaoLoginSetting() {
        kakaoCallBack = { token, error ->
            if (error != null) {
                Toast.makeText(requireContext(), "카카오로그인 실패", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                loginViewModel.kakaoAddToken(token)
                loginViewModel.kakaoEmail.observe(viewLifecycleOwner) {
                    apiPostLogin(it)
                }
            }
        }
    }

    private fun apiPostLogin(email: String) = with(binding) {
        val password = BuildConfig.SOCIAL_LOGIN_PASSWORFD

        loginViewModel.postLogin(LoginInfo(email, password))
        loginViewModel.userProfile.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    root.findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }
                is Resource.Error -> {
                    progressCircular.hide()

                    val action = LoginFragmentDirections.actionLoginFragmentToSetTechFragment(
                        UserEntity(
                            email = email,
                            user_pw = password,
                        )
                    )
                    root.findNavController().navigate(action)
                }
            }
        }
    }

    private fun buttonClick() = with(binding) {
        emailLoginButton.setOnClickListener {
            root.findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment)
        }

        googleLoginButton.setOnClickListener {
            googleGetResult.launch(googleSignInClient.signInIntent)
        }

        kakaoLoginButton.setOnClickListener {
            kakaoLoginSend()
        }
    }

    private fun kakaoLoginSend() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = kakaoCallBack)
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패")
                } else {
                    val kakaoEmail = user?.kakaoAccount?.email.toString()
                    loginViewModel.kakaoSetEmail(kakaoEmail)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kakaoCallBack)
        }
    }

}
