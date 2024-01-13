package com.example.palto.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.palto.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    // userViewModel is where the user is logged in, at the activity level.
    private val loginViewModel: LoginViewModel by activityViewModels() { LoginViewModel.Factory }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        val navController = findNavController()

        // Bind the login button.
        binding.login.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            loginViewModel.login(
                binding.hostname.text.toString(),
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        // Bind anonymous login clickable text.
        binding.loginAnonymous.setOnClickListener {
            loginViewModel.loginAnonymous()
        }

        // On result of logging.
        loginViewModel.result.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            if (it.success) {
                navController.popBackStack()
            } else if (it.error != null) {
                binding.loginError.text = "Exception : ${it.exception.toString()}"
                Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostnameEditText = binding.hostname
        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner) {
            if (it == null) {
                return@Observer
            }
            loginButton.isEnabled = it.isDataValid
            it.hostnameError?.let {
                hostnameEditText.error = getString(it)
            }
            it.usernameError?.let {
                usernameEditText.error = getString(it)
            }
            it.passwordError?.let {
                passwordEditText.error = getString(it)
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    //findNavController().navigate(R.id.action_loginFragment_to_attendanceFragment)
                    //updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    hostnameEditText.text.toString(),
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        hostnameEditText.addTextChangedListener(afterTextChangedListener)
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    hostnameEditText.text.toString(),
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }
    }
     */

    /*
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

     */
}