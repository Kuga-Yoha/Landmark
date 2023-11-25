package com.group6.landmark

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LandmarkDetailActivity : AppCompatActivity() {

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var landmark: Landmark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_detail)

        landmark = intent.getParcelableExtra("landmark")!!

        val imageViewLandmark = findViewById<ImageView>(R.id.imageViewLandmark)
        imageViewLandmark.setImageResource(landmark.imagePlaceholder)

        mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            val location = LatLng(landmark.latitude, landmark.longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(landmark.name))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }
}
