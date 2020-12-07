package com.example.noforeignland_exam_pgr208.data.model.places


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Places(
    val geometry: Geometry,
    val properties: Properties
) : Parcelable