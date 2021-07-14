package com.github.labibmuhajir.myapplication.ui.artisandetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.labibmuhajir.myapplication.common.load
import com.github.labibmuhajir.myapplication.common.snackbar
import com.github.labibmuhajir.myapplication.common.viewBinding
import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.databinding.ActivityArtisanBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtisanDetailActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityArtisanBinding::inflate)
    private val viewModel by viewModel<ArtisanDetailViewModel>()

    private val adapter by lazy { ServiceAdapter() }

    companion object {
        private const val EXTRA_ID = "extra_id"
        fun intent(context: Context, id: String) = Intent(context, ArtisanDetailActivity::class.java).apply {
            putExtra(EXTRA_ID, id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeData()
        intent.getStringExtra(EXTRA_ID)?.let {
            viewModel.getArtisan(it)
        }
    }

    private fun observeData() {
        viewModel.artisanState.observe(this) {
            when(it) {
                is ViewState.Loading -> {}
                is ViewState.Success -> bind(it.data)
                is ViewState.Error -> binding.root.snackbar(it.message).show()
            }
        }
    }

    private fun bind(artisan: Artisan) {
        adapter.data = artisan.services.toMutableList()
        with(binding) {
            with(viewItem) {
                ivArtisan.load(artisan.avatar)
                tvName.text = artisan.name
                tvDescription.text = artisan.description
            }
            rvService.adapter = adapter
        }
    }
}