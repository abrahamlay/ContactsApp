package com.abrahamlay.contactsapp.repository

/**
 * Created by Abraham on 18/10/2018.
 */

interface RemoteCallback<T> {
    fun onDataLoaded(data: T)
}
