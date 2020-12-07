package com.example.noforeignland_exam_pgr208.data.db

import android.provider.BaseColumns

object Table : BaseColumns {
    const val PLACES_TABLE_NAME = "place_table"
    const val COLUMN_ID = "_id"
    const val COLUMN_PLACE_ID = "place_id"
    const val COLUMN_PLACE_ID_TYPE = "TEXT"
    const val COLUMN_NAME = "place_name"
    const val COLUMN_NAME_TYPE = "TEXT"
    const val COLUMN_LAT = "place_lat"
    const val COLUMN_LAT_TYPE = "REAL"
    const val COLUMN_LNG = "place_lng"
    const val COLUMN_LNG_TYPE = "REAL"
}