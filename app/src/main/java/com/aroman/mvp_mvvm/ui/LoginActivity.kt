package com.aroman.mvp_mvvm.ui

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.aroman.mvp_mvvm.app
import com.aroman.mvp_mvvm.databinding.ActivityMainBinding
import java.lang.Thread.sleep

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = restoreViewModel()

        binding.loginButton.setOnClickListener {
            viewModel.onLoginAttempt(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.registerButton.setOnClickListener {
            viewModel.onRegisterNewUser(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.forgotPasswordButton.setOnClickListener {
            viewModel.onForgotPassword(
                binding.loginEditText.text.toString(),
            )
        }

        viewModel.shouldShowProgress.subscribe { shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel.isSuccess.subscribe { isSuccess ->
            if (isSuccess == true) {
                setSuccess()
            }
        }

        viewModel.errorText.subscribe { it?.let { setError(it) } }
    }

    private fun restoreViewModel(): LoginContract.ViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginContract.ViewModel
        return viewModel ?: LoginViewModel(app.loginUsecase, app.dbUserRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        binding.root.setBackgroundColor(Color.GREEN)
        Thread {
            sleep(2_000)
            binding.root.setBackgroundColor(Color.WHITE)
            runOnUiThread { hideProgress() }
        }.start()
        hideKeyboard(this)
    }


    private fun setError(error: String) {
        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        binding.root.setBackgroundColor(Color.RED)
        Thread {
            sleep(2_000)
            binding.root.setBackgroundColor(Color.WHITE)
            runOnUiThread { hideProgress() }
        }.start()
    }

    private fun showProgress() {
        binding.loginButton.isEnabled = false
        binding.registerButton.isEnabled = false
        binding.forgotPasswordButton.isEnabled = false
        binding.loginProgressBar.visibility = View.VISIBLE
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.registerButton.isEnabled = true
        binding.forgotPasswordButton.isEnabled = true
        binding.loginProgressBar.visibility = View.INVISIBLE
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}