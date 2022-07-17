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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    private lateinit var googleGetResult: ActivityResultLauncher<Intent>

    private lateinit var kakaoCallBack: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        googleGetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.firebaseAuthWithGoogle(account.idToken!!)
                    Toast.makeText(context, "구글로그인 성공", Toast.LENGTH_SHORT).show()
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

        val application = requireNotNull(this).activity?.application
        val factory = LoginViewModelFactory(application!!, object : OnSignInStartedListener {
            override fun onSignInStarted(client: GoogleSignInClient?) {
                googleGetResult.launch(client?.signInIntent)
            }
        })

        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.emailLoginButton.setOnClickListener { binding.root.findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment) }
        binding.googleLoginButton.setOnClickListener { viewModel.googleSignIn() }
        binding.kakaoLoginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = kakaoCallBack)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kakaoCallBack)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
