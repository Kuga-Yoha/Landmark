package com.group6.landmark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LandmarkTypeActivity : AppCompatActivity(), CustomAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_type)

        val landmarks = intent.getParcelableArrayListExtra<Landmark>("landmarks") ?: emptyList()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLandmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(landmarks, this)
    }

    override fun onItemClick(landmark: Landmark) {
        val intent = Intent(this, LandmarkDetailActivity::class.java)
        intent.putExtra("landmark", landmark)
        startActivity(intent)
    }
}
