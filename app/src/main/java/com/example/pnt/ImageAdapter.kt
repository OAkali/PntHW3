package com.example.pnt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pnt.databinding.ItemImageBinding

class ImageAdapter(private val list: ArrayList<ImageModel>) : Adapter<ImageAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    fun addNewImage(list: ArrayList<ImageModel>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(list[position])
    }

    inner class Holder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(image: ImageModel) {
            binding.apply {
                pixelImage.load(image.largeImageURL)
            }
        }
    }
}