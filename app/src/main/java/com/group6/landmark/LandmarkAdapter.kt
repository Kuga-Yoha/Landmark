package com.group6.landmark


import Landmark
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandmarkAdapter (private val context: Context, private val type: String) : RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {


    private  val landmarks = getLandmarksByType(type)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.landmark_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.landmark.text = landmarks[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, LandmarkMap::class.java)
            intent.putExtra("Landmark",  landmarks[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = landmarks.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val landmark: TextView = itemView.findViewById(R.id.landmark_item_tv)
    }

    private fun getLandmarksByType(type: String): List<Landmark> {
        return when (type) {
            "Old Buildings" -> listOf(
                Landmark("Old Mill", "21 Old Mill Rd, Etobicoke, ON M8X 1G5", 43.6519, -79.4944),
                Landmark("Casa Loma", "1 Austin Terrace, Toronto, ON M5R 1X8", 43.6780, -79.4097)
            )
            "Museums" -> listOf(
                Landmark("Ontario Science Centre", "770 Don Mills Rd, North York, ON M3C 1T3", 43.7163, -79.3407),
                Landmark("Campbell House Museum", "160 Queen St W, Toronto, ON M5H 3H3", 43.6517, -79.3707)
            )
            "Stadiums" -> listOf(
                Landmark("BMO Field", "170 Princes' Blvd, Toronto, ON M6K 3C3", 43.6334, -79.4188),
                Landmark("Scotiabank Arena", "40 Bay St, Toronto, ON M5J 2X2", 43.6435, -79.3791)
            )
            "Attractions" -> listOf(
                Landmark("CN Tower", "301 Front St W, Toronto, ON M5V 2T6", 43.6426, -79.3871),
                Landmark("Distillery Historic District", "55 Mill St, Toronto, ON M5A 3C4", 43.6505, -79.3595)
            )
            else -> emptyList()
        }
    }

}