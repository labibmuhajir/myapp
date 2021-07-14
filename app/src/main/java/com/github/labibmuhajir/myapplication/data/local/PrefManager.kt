package com.github.labibmuhajir.myapplication.data.local

import android.content.Context
import com.github.labibmuhajir.myapplication.data.model.User
import com.google.gson.Gson

class PrefManager(context: Context, private val gson: Gson) {
    private val pref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    companion object {
        const val KEY_USER = "pref_user"
    }

    var user: User?
        get() = gson.fromJson(pref.getString(KEY_USER, null), User::class.java)
        set(value) {
            pref.edit().putString(KEY_USER, gson.toJson(value)).apply()
        }

    fun clear() {
        pref.edit().clear().apply()
    }
}