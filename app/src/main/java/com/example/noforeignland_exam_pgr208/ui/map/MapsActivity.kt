package com.example.noforeignland_exam_pgr208.ui.map

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.noforeignland_exam_pgr208.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var lon: Double? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        lat = intent.getDoubleExtra("lat", 2.2)
        lon = intent.getDoubleExtra("lon", 2.2)
        name = intent.getStringExtra("name")

        // <- back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadMarker(lat: Double, lon: Double, name: String) {
        val latAndLong = LatLng(lat, lon)
        val placeName = name
        mMap.addMarker(MarkerOptions().position(latAndLong).title(placeName).snippet(latAndLong.toString()))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latAndLong))

        //Zooming map
        val zoomLevel = 15.0f
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latAndLong, zoomLevel))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        lat?.let { lon?.let { it1 -> name?.let { it2 -> loadMarker(it, it1, it2) } } }
    }

    //Map back button, returns to the screen from which it was "activated"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
