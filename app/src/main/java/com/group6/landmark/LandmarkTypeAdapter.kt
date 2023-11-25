package com.group6.landmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandmarkTypeAdapter(private val types: List<String>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<LandmarkTypeAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(type: String)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewType: TextView = itemView.findViewById(R.id.textViewType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_landmark_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = types[position]
        holder.textViewType.text = type
        holder.itemView.setOnClickListener { listener.onItemClick(type) }
    }

    override fun getItemCount(): Int = types.size
}
