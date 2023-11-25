package com.group6.landmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), LandmarkTypeAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val landmarkTypes = listOf("Old Buildings", "Museums", "Stadiums", "Attractions")

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLandmarkTypes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LandmarkTypeAdapter(landmarkTypes, this)
    }

    override fun onItemClick(type: String) {
        val landmarks = when (type) {
            "Old Buildings" -> listOf(
                Landmark("Casa Loma", "Old Building", "1 Austin Terrace, Toronto, ON", 43.6780, -79.4094, R.drawable.casa_loma)
            )
            "Museums" -> listOf(
                Landmark("Royal Ontario Museum", "Museum", "100 Queen's Park, Toronto, ON", 43.6677, -79.3948, R.drawable.rom)
            )
            "Stadiums" -> listOf(
                Landmark("Scotiabank Arena", "Stadium", "40 Bay St, Toronto, ON", 43.6435, -79.3791, R.drawable.scotiabank_arena)
            )
            "Attractions" -> listOf(
                Landmark("CN Tower", "Attraction", "301 Front St W, Toronto, ON", 43.6426, -79.3871, R.drawable.cn_tower)
            )
            else -> emptyList()
        }

        val intent = Intent(this, LandmarkTypeActivity::class.java)
        intent.putParcelableArrayListExtra("landmarks", ArrayList(landmarks))
        startActivity(intent)
    }
}
