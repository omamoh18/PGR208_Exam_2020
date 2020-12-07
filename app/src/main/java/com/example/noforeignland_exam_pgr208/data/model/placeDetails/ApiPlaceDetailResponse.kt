package com.example.noforeignland_exam_pgr208.data.model.placeDetails

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class ApiPlaceDetailResponse(
    val place: PlaceDetails
) : Parcelable