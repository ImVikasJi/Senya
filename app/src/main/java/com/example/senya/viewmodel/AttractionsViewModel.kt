package com.example.senya.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senya.data.Attraction
import com.example.senya.repository.AttractionsRepository

class AttractionsViewModel: ViewModel() {

    private val repository = AttractionsRepository()
    val attractionLiveData = MutableLiveData<List<Attraction>>()

    val selectionAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()

    val factSelectedLiveData = MutableLiveData<Attraction>()


    fun init(context: Context){
        val attractionList = repository.parseAttractions(context)
        attractionLiveData.postValue(attractionList)
    }

    fun onAttractionSelected(attractionId: String){
        val attraction =  attractionLiveData.value?.find {
            it.id == attractionId
        } ?: return

        selectionAttractionLiveData.postValue(attraction)
    }
}