package com.example.noforeignland_exam_pgr208.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noforeignland_exam_pgr208.data.repository.PlaceDetailsRepository

@Suppress("UNCHECKED_CAST")
class PlaceDetailsViewModelFactory(
    private val infoRepository: PlaceDetailsRepository,
    private val id: String
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceDetailsViewModel(infoRepository, id) as T
    }
}