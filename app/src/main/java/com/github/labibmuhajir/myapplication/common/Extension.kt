package com.github.labibmuhajir.myapplication.common

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun orUnknownError(string: String?) = if (string.isNullOrBlank()) "Error Occured" else string

fun View.snackbar(message: String): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
}

fun ImageView.load(url: String) {
    load(url)
}