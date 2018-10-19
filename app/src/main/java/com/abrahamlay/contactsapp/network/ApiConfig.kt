package com.abrahamlay.contactsapp.network

import com.abrahamlay.contactsapp.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by Abraham on 18/10/2018.
 */
object ApiConfig {
    val GSON = GsonBuilder()
        .create()!!
    const val BASE_URL = BuildConfig.BASE_URL

    const val CONTACT_URL="contact"
    const val CONTACT_WITH_ID_URL="contact/{id}"

    const val NETWORK_TIMEOUT = 40000

    val rxAdapter = RxJava2CallAdapterFactory.create()!!
}