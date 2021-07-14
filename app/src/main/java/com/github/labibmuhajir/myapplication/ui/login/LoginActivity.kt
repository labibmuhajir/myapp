package com.github.labibmuhajir.myapplication.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.labibmuhajir.myapplication.R
import com.github.labibmuhajir.myapplication.common.snackbar
import com.github.labibmuhajir.myapplication.common.viewBinding
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.databinding.ActivityLoginBinding
import com.github.labibmuhajir.myapplication.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private val viewModel by viewModel<LoginViewModel>()

    companion object {
        fun intent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListener()
        observeData()
    }

    private fun setupListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                val username = tilName.editText?.text.toString()
                val password = tilPassword.editText?.text.toString()

                when {
                    username.isBlank() -> {
                        binding.root.snackbar(getString(R.string.username_needed)).show()
                        tilName.requestFocus()
                    }
                    password.isBlank() -> {
                        binding.root.snackbar(getString(R.string.password_needed)).show()
                        tilPassword.requestFocus()
                    }
                    else -> {
                        viewModel.login(username, password)
                    }
                }
            }
        }

    }

    private fun observeData() {
        viewModel.loginState.observe(this) {
            when(it) {
                is ViewState.Loading -> {}
                is ViewState.Success -> {
                    val intent = MainActivity.intent(this).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                    finish()
                }
                is ViewState.Error -> binding.root.snackbar(it.message)
            }
        }
    }
}