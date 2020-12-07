package com.example.noforeignland_exam_pgr208.ui.places

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noforeignland_exam_pgr208.R
import com.example.noforeignland_exam_pgr208.data.api.Api
import com.example.noforeignland_exam_pgr208.data.db.dao.PlacesDao
import com.example.noforeignland_exam_pgr208.data.repository.PlacesRepository
import com.example.noforeignland_exam_pgr208.ui.details.PlaceDetailsActivity
import com.example.noforeignland_exam_pgr208.ui.map.MapsActivity
import com.example.noforeignland_exam_pgr208.ui.places.adapter.NoForeignLandAdapter
import com.example.noforeignland_exam_pgr208.ui.utils.ClickListener
import kotlinx.android.synthetic.main.activity_main.*

class PlacesActivity : AppCompatActivity(), ClickListener {

    private lateinit var adapter: NoForeignLandAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = Api()
        val placeDao = PlacesDao(this)
        val repository = PlacesRepository(api, placeDao)
        val viewModel by viewModels<PlacesViewModel> {
            PlacesViewModelFactory(repository)
        }
        viewModel.getPlaces().observe(this, Observer { places ->
            recycler_view_places.also {
                it.layoutManager = LinearLayoutManager(this)
                adapter = NoForeignLandAdapter(places, this)
                it.adapter = adapter
            }
        })
    }

    //Search
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)

    }

    // -> Details screen
    override fun onPlacesNameClick(id: String) {
        val intent = Intent(this, PlaceDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    // -> Map screen
    override fun onIconClick(lat: Double, lon: Double, name: String) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("lon", lon)
        intent.putExtra("name", name)
        Log.d("Error", "LatLng$lat$lon")
        startActivity(intent)
    }
}
