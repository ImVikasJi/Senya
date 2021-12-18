package com.example.senya.repository

import android.content.Context
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {

    fun parseAttractions(context: Context): ArrayList<Attraction> {
        val textFromFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return  adapter.fromJson(textFromFile)!!.attractions as ArrayList<Attraction>
    }

}