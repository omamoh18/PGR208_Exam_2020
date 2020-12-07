package com.example.noforeignland_exam_pgr208.ui.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noforeignland_exam_pgr208.data.repository.PlacesRepository

@Suppress("UNCHECKED_CAST")
class PlacesViewModelFactory(private val repository: PlacesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlacesViewModel(repository) as T
    }

}
