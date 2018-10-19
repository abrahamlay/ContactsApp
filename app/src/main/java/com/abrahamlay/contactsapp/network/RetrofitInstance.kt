package com.abrahamlay.contactsapp.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abraham on 18/10/2018.
 */
class RetrofitInstance(private val baseUrl:String,
                       private val converterFactory: GsonConverterFactory,
                       private val rxAdapter: RxJava2CallAdapterFactory
                        ){
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(rxAdapter)
            .build()
    }
}