package com.github.labibmuhajir.myapplication.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.labibmuhajir.myapplication.common.viewBinding
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.databinding.ActivitySplashBinding
import com.github.labibmuhajir.myapplication.ui.main.MainActivity
import com.github.labibmuhajir.myapplication.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private val viewModel by viewModel<SplashViewModel>()

    companion object {
        fun intent(context: Context) = Intent(context, SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeData()
        viewModel.isLoggedIn()
    }

    private fun observeData() {
        viewModel.loginState.observe(this) {
            if (it is ViewState.Success) {
                val intent = when (it.data) {
                    LoginState.LOGGED_OUT -> LoginActivity.intent(this)
                    LoginState.LOGGED_IN -> MainActivity.intent(this)
                }.apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
            }
        }
    }


}