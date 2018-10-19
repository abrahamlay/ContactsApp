package com.abrahamlay.contactsapp.ui.widget

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Abraham on 19/10/2018.
 */
object SnackbarComposer{
    fun showSnackbar(view: View?, message:String?, actionTitle:String?, listener: View.OnClickListener?){
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
            .setAction(actionTitle, listener).show()
    }}