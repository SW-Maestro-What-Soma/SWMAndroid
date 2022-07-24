package com.example.swmandroid.ui.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel by viewModel<LoginViewModel>()

    private lateinit var googleGetResult: ActivityResultLauncher<Intent>

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var kakaoCallBack: (OAuthToken?, Throwable?) -> Unit

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        //TODO 구글 로그인해서 처음인지 아닌지 확인하는 로직 추가해야함
        googleGetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.googleAddToken(account.idToken!!)
                    Toast.makeText(context, "구글로그인 성공", Toast.LENGTH_SHORT).show()

                } catch (e: ApiException) {
                    Toast.makeText(context, "구글로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun kakaoLoginSetting(){
        //TODO 카카오 로그인해서 처음인지 아닌지 확인하는 로직 추가해야함
        kakaoCallBack = { token, error ->
            if (error != null) {
                Toast.makeText(context, "카카오로그인 실패", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                viewModel.kakaoAddToken(token)
                Toast.makeText(context, "카카오로그인 성공", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buttonClick() = with(binding) {
        emailLoginButton.setOnClickListener { root.findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment) }
        googleLoginButton.setOnClickListener { googleGetResult.launch(googleSignInClient.signInIntent) }
        kakaoLoginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = kakaoCallBack)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kakaoCallBack)
            }
        }
    }

}
