package com.github.labibmuhajir.myapplication.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.labibmuhajir.myapplication.ui.artisan.ArtisanFragment
import com.github.labibmuhajir.myapplication.ui.profile.ProfileFragment

class HomeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val pages = listOf(
        ArtisanFragment(), ProfileFragment()
    )

    override fun getItemCount(): Int = pages.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun createFragment(position: Int): Fragment = pages[position]
}