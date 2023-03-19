package com.binaracademy.musikasiq.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.musikasiq.databinding.ListItemMostPopularBinding

class MostPopularAdapter(private val populars: List<String>): RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemMostPopularBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(popular: String) {
            binding.tvTitleSong.text = popular
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemMostPopularBinding = ListItemMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = populars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(populars[position])
    }

}