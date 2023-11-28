// LandmarkTypeAdapter.kt
package com.group6.landmark

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandmarkTypeAdapter(private val context: Context) :
    RecyclerView.Adapter<LandmarkTypeAdapter.ViewHolder>() {

    private val landmarkTypes = arrayOf("Old Buildings", "Museums", "Stadiums", "Attractions")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landmark_type_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = landmarkTypes[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, LandmarkList::class.java)
            intent.putExtra("type_of_landmark", landmarkTypes[position])
            context.startActivity(intent)
        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return landmarkTypes.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.landmark_type_item_tv)
    }
}