package com.kevin.shyriapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.shyriapp.databinding.ItemshyriBinding

class ShyriAdapter : Adapter<ShyriAdapter.shyriHolder>() {

    var shyriList = ArrayList<ShyriModel>()
    lateinit var context: Context

    class shyriHolder(itemView: ItemshyriBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): shyriHolder {
        context = parent.context
        var binding = ItemshyriBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return shyriHolder(binding)
    }

    override fun getItemCount(): Int {
        return shyriList.size
    }

    override fun onBindViewHolder(holder: shyriHolder, position: Int) {
        holder.binding.apply {
            shyriList.get(position).apply {
                txtshyri.text = shyriList.toString()
            }
        }
    }

    fun setShyri(shyriList: ArrayList<ShyriModel>) {
        this.shyriList = shyriList
    }
}