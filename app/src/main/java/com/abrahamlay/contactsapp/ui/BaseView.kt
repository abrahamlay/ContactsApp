package com.abrahamlay.contactsapp.ui

/**
 * Created by Abraham on 18/10/2018.
 */
interface BaseView{
    fun showLoading(active: Boolean)

    fun showEmpty(message:String?)

    fun onUnknownError(code: Int, errorMessage: String?)

    fun onTimeout()

    fun onNetworkError()
}