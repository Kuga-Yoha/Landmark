package com.group6.landmark

import Landmark
import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LandmarkMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var landmark: Landmark
    lateinit var geofencingClient: GeofencingClient
    private lateinit var geofencePendingIntent: PendingIntent
    private lateinit var geofenceCircle: Circle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_map)

        landmark = intent.getSerializableExtra("Landmark") as Landmark
        geofencingClient = LocationServices.getGeofencingClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        geofencePendingIntent = createGeofencePendingIntent()


    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val location = LatLng(landmark.latitude, landmark.longitude)
        map.addMarker(MarkerOptions().position(location).title(landmark.name))
        map.moveCamera(CameraUpdateFactory.newLatLng(location))

        map.setOnMapLongClickListener{
            val geofence = createGeofence(location)
            val geofencingRequest = createGeofencingRequest(geofence)
            addGeofence(geofencingRequest)
        }
        geofenceCircle = map.addCircle(
            CircleOptions()
                .center(location)
                .radius(GEOFENCE_RADIUS_IN_METERS.toDouble())
                .strokeColor(Color.RED)
                .fillColor(Color.argb(70, 255, 0, 0))
        )
    }




    private fun createGeofence(latLng: LatLng): Geofence {
        return Geofence.Builder()
            .setRequestId(landmark.name)
            .setCircularRegion(latLng.latitude, latLng.longitude, GEOFENCE_RADIUS_IN_METERS)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()
    }

    private fun createGeofencingRequest(geofence: Geofence): GeofencingRequest {
        return GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()
    }

    private fun createGeofencePendingIntent(): PendingIntent {
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        return PendingIntent
            .getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun addGeofence(geofencingRequest: GeofencingRequest) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener {
                Toast.makeText(this, "Geofence added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to add geofence", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val GEOFENCE_RADIUS_IN_METERS = 100f
    }
}