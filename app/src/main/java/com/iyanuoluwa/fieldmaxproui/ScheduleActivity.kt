package com.iyanuoluwa.fieldmaxproui

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var lastLocation: Location
    private lateinit var mMap : GoogleMap

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    private lateinit var adapter: RecyclerViewAdapter
    private val viewModel: SchedulesViewModel by viewModels {
        ViewModelFactory((application as SchedulesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(viewModel.getSchedules())
        recyclerView.adapter = adapter

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        val cardView = findViewById<CardView>(R.id.card_view)
        val numberOfSchedules = findViewById<TextView>(R.id.number_of_schedules)
        val dash = findViewById<ImageView>(R.id.dash)
        val relativeLayout = findViewById<RelativeLayout>(R.id.relative_bottom)
        val bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet)).apply {
            peekHeight = 250
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    floatingActionButton.visibility = View.GONE
                    numberOfSchedules.visibility = View.GONE
                    dash.visibility = View.GONE
                    cardView.visibility = View.VISIBLE
                    relativeLayout.visibility = View.VISIBLE
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    floatingActionButton.visibility = View.VISIBLE
                    numberOfSchedules.visibility = View.VISIBLE
                    dash.visibility = View.VISIBLE
                    cardView.visibility = View.GONE
                    relativeLayout.visibility = View.GONE
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        mapFragment= supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLocation = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLocation)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16F))
            }
        }
    }

    private fun placeMarkerOnMap(currentLocation: LatLng) {

        val markerOptions = MarkerOptions().position(currentLocation)
        markerOptions.title("you are here")
        mMap.addMarker(markerOptions)

    }

}