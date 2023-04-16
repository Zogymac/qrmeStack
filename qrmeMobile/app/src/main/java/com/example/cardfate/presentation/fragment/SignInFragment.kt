package com.example.cardfate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentSignInBinding
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.state.AuthError
import com.example.cardfate.presentation.state.AuthProgress
import com.example.cardfate.presentation.state.AuthSuccess
import com.example.cardfate.presentation.viewmodel.SignInViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

    private var errorToast: Toast? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val signInViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.btSignIn.setOnClickListener {
            val login = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            signInViewModel.signIn(login, password)
        }
        binding.btLogIn.setOnClickListener {
            navigateToFragment(LogInFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun observeViewModel() {
        signInViewModel.authState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btSignIn.isEnabled = true

            when (it) {
                is AuthSuccess -> {
                    navigateToFragment(MainFragment())
                    updateSharedPreferences(it.login)
                }
                is AuthProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btSignIn.isEnabled = false
                }
                is AuthError -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun updateSharedPreferences(login: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putString(MainActivity.LOGIN, login).apply()
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            SignInViewModel.ERROR_EMPTY_LOGIN -> {
                showErrorToast("Все поля должны быть заполнены")
            }
            SignInViewModel.ERROR_EMPTY_PASSWORD -> {
                showErrorToast("Все поля должны быть заполнены")
            }
            SignInViewModel.ERROR_SUCH_USER_EXISTS -> {
                showErrorToast("Пользователь с таким логином уже существует")
            }
        }
    }

    private fun showErrorToast(errorText: String) {
        errorToast?.cancel()
        errorToast = Toast.makeText(
            requireContext(),
            errorText, Toast.LENGTH_SHORT
        )
        errorToast?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val IS_LOGGED = "is_logged"
    }
}