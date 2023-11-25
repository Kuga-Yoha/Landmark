package com.group6.landmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: List<Landmark>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(landmark: Landmark)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLandmark: TextView = view.findViewById(R.id.textViewLandmark)
        val imageViewLandmark: ImageView = view.findViewById(R.id.imageViewLandmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_landmark, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val landmark = dataSet[position]
        holder.textViewLandmark.text = landmark.name
        holder.imageViewLandmark.setImageResource(landmark.imagePlaceholder)
        holder.itemView.setOnClickListener { listener.onItemClick(landmark) }
    }

    override fun getItemCount() = dataSet.size
}

