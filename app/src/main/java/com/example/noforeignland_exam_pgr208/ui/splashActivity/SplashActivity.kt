package com.example.noforeignland_exam_pgr208.ui.splashActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noforeignland_exam_pgr208.ui.places.PlacesActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, PlacesActivity::class.java)
        startActivity(intent)
        finish()
    }
}