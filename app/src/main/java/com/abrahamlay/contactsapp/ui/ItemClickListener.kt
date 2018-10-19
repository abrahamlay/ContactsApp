package com.abrahamlay.contactsapp.ui

import android.view.View

/**
 * Created by Abraham on 24/04/2018.
 */
interface ItemClickListener{
    fun onItemClicked(view: View, item: Any, position: Int)
}