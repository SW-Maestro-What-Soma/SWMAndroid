package com.example.swmandroid.screen.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<LoginViewModel>()

    private lateinit var googleGetResult: ActivityResultLauncher<Intent>

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var kakaoCallBack: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    viewModel.googleAddToken(account.idToken!!)
                    Toast.makeText(context, "구글로그인 성공", Toast.LENGTH_SHORT).show()

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.getProfile()
                    }
                } catch (e: ApiException) {
                    Toast.makeText(context, "구글로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

        kakaoCallBack = { token, error ->
            if (error != null) {
                Toast.makeText(context, "카카오로그인 실패", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                viewModel.kakaoAddToken(token)
                Toast.makeText(context, "카카오로그인 성공", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        buttonClick()

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
