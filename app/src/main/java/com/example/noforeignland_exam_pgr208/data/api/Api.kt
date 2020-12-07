package com.example.noforeignland_exam_pgr208.data.api

import com.example.noforeignland_exam_pgr208.data.model.places.ApiPlacesResponse
import com.example.noforeignland_exam_pgr208.data.model.placeDetails.ApiPlaceDetailResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/home/api/v1/places")
    suspend fun getPlaces(): Response<ApiPlacesResponse>

    @GET("home/api/v1/place")
    suspend fun getPlaceDetails(@Query("id") id: String?): Response<ApiPlaceDetailResponse>

    companion object {
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.noforeignland.com")
                .build()
                .create(Api::class.java)
        }
    }
}
