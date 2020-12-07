package com.example.noforeignland_exam_pgr208.data.db.dao

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import com.example.noforeignland_exam_pgr208.data.db.utils.DatabaseHandler
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LAT
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LNG
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_NAME
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_PLACE_ID
import com.example.noforeignland_exam_pgr208.data.db.Table.PLACES_TABLE_NAME
import com.example.noforeignland_exam_pgr208.data.model.places.Geometry
import com.example.noforeignland_exam_pgr208.data.model.places.Places
import com.example.noforeignland_exam_pgr208.data.model.places.Properties

class PlacesDao(ctx: Context) {

    private var db: DatabaseHandler = DatabaseHandler.getInstance(ctx)!!

    fun addList(places: List<Places>) {

        // Begin transaction
        db.writableDatabase.beginTransaction()

        val query =
            "INSERT OR IGNORE INTO $PLACES_TABLE_NAME ($COLUMN_PLACE_ID, $COLUMN_NAME, $COLUMN_LAT, $COLUMN_LNG) VALUES (?, ?, ?, ?)"

        val stmt: SQLiteStatement = db.writableDatabase.compileStatement(query)

        // Iterate over list of places
        places.forEach { place ->
            stmt.run {

                // Properties
                place.properties.id.let { bindString(1, it) }
                bindString(2, place.properties.name)

                // Geometry
                bindDouble(3, place.geometry.coordinates[0])
                bindDouble(4, place.geometry.coordinates[1])

                stmt.execute()
                stmt.clearBindings()
            }
        }

        // Set
        db.writableDatabase.setTransactionSuccessful()
        // End
        db.writableDatabase.endTransaction()
        // Close
        db.writableDatabase.close()
    }

    fun fetchAll(): MutableList<Places> {

        val cursor: Cursor =
            db.readableDatabase.query(PLACES_TABLE_NAME, null, null, null, null, null, null)
        val placeList = mutableListOf<Places>()

        with(cursor) {
            while (moveToNext()) {
                val placeId: String = getString(getColumnIndexOrThrow(COLUMN_PLACE_ID))
                val placeName: String = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val placeLat: Double = getDouble(getColumnIndexOrThrow(COLUMN_LAT))
                val placeLng: Double = getDouble(getColumnIndexOrThrow(COLUMN_LNG))

                val properties = Properties(placeId, placeName)
                val geometry = Geometry(listOf(placeLat, placeLng))
                val place = Places(geometry, properties)
                placeList.add(place)
            }
        }

        db.writableDatabase.close()
        return placeList
    }

}

