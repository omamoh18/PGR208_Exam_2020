package com.example.noforeignland_exam_pgr208.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noforeignland_exam_pgr208.data.model.placeDetails.PlaceDetails
import com.example.noforeignland_exam_pgr208.data.repository.PlaceDetailsRepository
import kotlinx.coroutines.launch

class PlaceDetailsViewModel(
    private val repository: PlaceDetailsRepository,
    private val id: String
) : ViewModel() {

    private var placeDetailsLiveData = MutableLiveData<PlaceDetails>()

    init {
        viewModelScope.launch {
            val detail = repository.getDetails(id)
            placeDetailsLiveData.postValue(detail)
        }
    }

    fun getPlacesDetails(): MutableLiveData<PlaceDetails> = placeDetailsLiveData
}