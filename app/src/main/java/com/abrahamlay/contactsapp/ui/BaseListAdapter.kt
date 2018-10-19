package com.abrahamlay.contactsapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


/**
 * Created by Abraham on 18/10/2018.
 */
abstract class BaseListAdapter<T: RecyclerView.ViewHolder>(
                  private val itemList:List<Any?>?) : RecyclerView.Adapter<T>() {

    override fun getItemCount(): Int = itemList!!.size

}