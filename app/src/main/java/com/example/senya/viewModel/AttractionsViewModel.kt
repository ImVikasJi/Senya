package com.example.senya.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senya.data.Attraction
import com.example.senya.repository.AttractionsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionsViewModel : ViewModel() {

    private val repository = AttractionsRepository()
    val attractionLiveData = MutableLiveData<ArrayList<Attraction>>()

    val selectionAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()

    val factSelectedLiveData = MutableLiveData<Attraction>()

    val searchQuery = MutableLiveData<Attraction>()

    fun init(context: Context) {
        viewModelScope.launch {
            delay(5000)
            val attractionList = repository.parseAttractions(context)
            attractionLiveData.postValue(attractionList)
        }
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionLiveData.value?.find {
            it.id == attractionId
        } ?: return

        selectionAttractionLiveData.postValue(attraction)
    }
}