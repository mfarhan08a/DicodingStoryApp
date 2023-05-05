package com.mfarhan08a.dicodingstoryapp.view.login

import android.animation.ObjectAnimator
import android.app.LocaleManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.LocaleListCompat
import com.mfarhan08a.dicodingstoryapp.R
import com.mfarhan08a.dicodingstoryapp.view.main.MainActivity
import com.mfarhan08a.dicodingstoryapp.view.register.RegisterActivity
import com.mfarhan08a.dicodingstoryapp.databinding.ActivityLoginBinding
import com.mfarhan08a.dicodingstoryapp.utils.ViewModelFactory
import com.mfarhan08a.dicodingstoryapp.data.Result

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.title_login)

        loginViewModel.getLocaleSetting().observe(this) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                this.getSystemService(LocaleManager::class.java).applicationLocales =
                    LocaleList.forLanguageTags(it)
            } else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(it))
            }
        }

        loginViewModel.apply {
            getToken().observe(this@LoginActivity) {
                if (it != null) {
                    toMainActivity()
                }
            }
        }


        binding.apply {
            btnLogin.setOnClickListener {
                val email = edLoginEmail.text.toString().trim()
                val password = edLoginPassword.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8) {
                        login(email, password)
                    } else {
                        Toast.makeText(
                            this@LoginActivity, getString(R.string.invalid_data), Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity, getString(R.string.enter_data), Toast.LENGTH_SHORT
                    ).show()
                }
            }
            tvRegister.setOnClickListener { toRegisterActivity() }
        }

        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

    private fun toRegisterActivity() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun toMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun login(email: String, password: String) {
        binding.apply {
            loginViewModel.login(email, password).observe(this@LoginActivity) {
                when (it) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        toMainActivity()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            this@LoginActivity, getString(R.string.login_failed), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setButtonEnable() {
        binding.apply {
            val email = edLoginEmail.text.toString()
            val password = edLoginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                btnLogin.isEnabled =
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8
            } else {
                btnLogin.isEnabled = false
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}