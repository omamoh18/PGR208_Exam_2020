package com.example.noforeignland_exam_pgr208.data.repository

import com.example.noforeignland_exam_pgr208.data.api.Api
import com.example.noforeignland_exam_pgr208.data.api.SafeApiRequest
import com.example.noforeignland_exam_pgr208.data.model.placeDetails.PlaceDetails

class PlaceDetailsRepository(private val api: Api) : SafeApiRequest() {

    suspend fun getDetails(id: String?): PlaceDetails {
        val detailResponse = apiRequest { api.getPlaceDetails(id) }
        return detailResponse.place
    }

}