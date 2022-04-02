package com.aroman.mvp_mvvm

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import com.aroman.mvp_mvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Thread.sleep

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter.onLoginAttempt(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.registerButton.setOnClickListener {
            presenter.onRegisterNewUser(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.forgotPasswordButton.setOnClickListener {
            presenter.onForgotPassword(
                binding.loginEditText.text.toString(),
            )
        }
    }

    private fun restorePresenter(): LoginContract.LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginContract.LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        binding.root.setBackgroundColor(Color.GREEN)
        Thread {
            sleep(2_000)
            binding.root.setBackgroundColor(Color.WHITE)
            runOnUiThread { hideProgress() }
        }.start()
        hideKeyboard(this)
        hideProgress()
    }


    @MainThread
    override fun setError(error: String) {
        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        binding.root.setBackgroundColor(Color.RED)
        Thread {
            sleep(2_000)
            binding.root.setBackgroundColor(Color.WHITE)
            runOnUiThread { hideProgress() }
        }.start()
    }

    @MainThread
    override fun setMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        binding.loginButton.isEnabled = false
        binding.registerButton.isEnabled = false
        binding.forgotPasswordButton.isEnabled = false
        hideKeyboard(this)
    }

    @MainThread
    override fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.registerButton.isEnabled = true
        binding.forgotPasswordButton.isEnabled = true
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}