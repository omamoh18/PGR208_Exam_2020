package com.example.noforeignland_exam_pgr208.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noforeignland_exam_pgr208.data.model.places.Places
import com.example.noforeignland_exam_pgr208.data.repository.PlacesRepository
import kotlinx.coroutines.launch

class PlacesViewModel(private val repository: PlacesRepository) : ViewModel() {


    private val placesLiveData = MutableLiveData<MutableList<Places>>()

    init {
        viewModelScope.launch {
            val places = repository.getPlaces()
            placesLiveData.postValue(places)
        }
    }

    fun getPlaces(): LiveData<MutableList<Places>> = placesLiveData

}
