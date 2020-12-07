package com.example.noforeignland_exam_pgr208.data.model.placeDetails

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class PlaceDetails(
    val name: String,
    val lat: Double,
    val lon: Double,
    val comments: String,
    val banner: String
) : Parcelable