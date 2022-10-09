package com.awais.hilt.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awais.hilt.databinding.ItemcategoryBinding
import com.awais.hilt.ui.main.models.Categories


class CategoryAdapter(
    private val mList: ArrayList<Categories>,

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemcategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemViewHolder(val binding: ItemcategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        holder.binding.title.text = mList[position].name

    }

}