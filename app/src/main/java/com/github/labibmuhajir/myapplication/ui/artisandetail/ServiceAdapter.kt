package com.github.labibmuhajir.myapplication.ui.artisandetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.labibmuhajir.myapplication.data.http.response.Service
import com.github.labibmuhajir.myapplication.databinding.ItemServiceBinding

class ServiceAdapter : RecyclerView.Adapter<ServiceAdapter.ServiceHolder>() {
    var data = mutableListOf<Service>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val binding = ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ServiceHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: Service) {
            with(binding) {
                tvName.text = service.name
                tvPrice.text = service.price
                tvDescription.text = service.caption
            }
        }
    }
}