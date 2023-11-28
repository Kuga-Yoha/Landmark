package com.group6.landmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LandmarkList : AppCompatActivity() {

    private lateinit var lAdapter: LandmarkAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_list)

        val type = intent.getStringExtra("type_of_landmark") as String


        recyclerView = findViewById(R.id.recycler_view_landmark)
        recyclerView.layoutManager = LinearLayoutManager(this)
        lAdapter = LandmarkAdapter(this, type)
        recyclerView.adapter = lAdapter


    }
}