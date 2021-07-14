package com.github.labibmuhajir.myapplication.ui.artisan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.labibmuhajir.myapplication.common.load
import com.github.labibmuhajir.myapplication.data.http.response.Artisan
import com.github.labibmuhajir.myapplication.databinding.ItemArtisanBinding

class ArtisanAdapter(private val listener: (artisan: Artisan) -> Unit) :
    RecyclerView.Adapter<ArtisanAdapter.ArtisanHolder>() {
    var data = mutableListOf<Artisan>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtisanHolder {
        val binding = ItemArtisanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtisanHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtisanHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ArtisanHolder(private val binding: ItemArtisanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artisan: Artisan) {
            with(binding) {
                ivArtisan.load(artisan.avatar)
                tvName.text = artisan.name
                tvDescription.text = artisan.description

                root.setOnClickListener { listener(artisan) }
            }
        }
    }
}