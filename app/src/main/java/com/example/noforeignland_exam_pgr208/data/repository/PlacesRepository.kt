package com.example.noforeignland_exam_pgr208.data.repository

import android.util.Log
import com.example.noforeignland_exam_pgr208.data.api.Api
import com.example.noforeignland_exam_pgr208.data.api.SafeApiRequest
import com.example.noforeignland_exam_pgr208.data.db.dao.PlacesDao
import com.example.noforeignland_exam_pgr208.data.model.places.Places

class PlacesRepository(private val api: Api, private val dao: PlacesDao) : SafeApiRequest() {

    suspend fun getPlaces(): MutableList<Places> {

        if (dao.fetchAll().isNotEmpty()) {
            Log.e("db", dao.fetchAll().size.toString())
            return dao.fetchAll()
        } else {
            Log.e("db", dao.fetchAll().size.toString())
            val placeResponse = apiRequest { api.getPlaces() }
            dao.addList(placeResponse.features)
            return placeResponse.features.toMutableList()
        }
    }
}




