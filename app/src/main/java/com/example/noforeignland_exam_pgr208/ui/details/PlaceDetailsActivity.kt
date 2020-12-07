package com.example.noforeignland_exam_pgr208.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.example.noforeignland_exam_pgr208.R
import com.example.noforeignland_exam_pgr208.data.api.Api
import com.example.noforeignland_exam_pgr208.data.repository.PlaceDetailsRepository
import com.example.noforeignland_exam_pgr208.ui.map.MapsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_place_details.*

class PlaceDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)

        //Hide the name in actionbar
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val api = Api()
        val infoRepository = PlaceDetailsRepository(api)
        val id = intent.getStringExtra("id")
        val viewModel by viewModels<PlaceDetailsViewModel> {
            PlaceDetailsViewModelFactory(
                infoRepository, id!!
            )
        }

        viewModel.getPlacesDetails().observe(this, Observer { info ->
            name_2.text = info.name

            //To "hide" the tags
            if (info.comments.isNotEmpty())
                place_details.text = HtmlCompat.fromHtml(
                    info.comments, HtmlCompat.FROM_HTML_MODE_LEGACY
                )

            // To check if banner is not null
            if (info.banner.isNotEmpty()) {
                Picasso.get().load(info.banner).resize(1200, 700).centerCrop().into(place_image)
            }

            // <- back button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // -> map
            icon.setOnClickListener {
                val intent = Intent(
                    this,
                    MapsActivity::class.java
                )
                intent.putExtra("lat", info.lat)
                intent.putExtra("lon", info.lon)
                intent.putExtra("name", info.name)
                startActivity(intent)
            }
        })
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