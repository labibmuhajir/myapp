package com.github.labibmuhajir.myapplication.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.github.labibmuhajir.myapplication.R
import com.github.labibmuhajir.myapplication.common.viewBinding
import com.github.labibmuhajir.myapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel<MainViewModel>()

    companion object {
        fun intent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        val adapter = HomeAdapter(supportFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_artisan -> {
                        viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.menu_profile -> {
                        viewPager.setCurrentItem(1, false)
                        true
                    }
                    else -> false
                }
            }
        }
    }


}

