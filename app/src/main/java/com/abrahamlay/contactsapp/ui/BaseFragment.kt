package com.abrahamlay.contactsapp.ui

import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.abrahamlay.contactsapp.R

/**
 * Created by Abraham on 18/10/2018.
 */
abstract class BaseFragment: Fragment(), BaseView {

    override fun onUnknownError(code: Int, errorMessage: String?) {
        Toast.makeText(context, "$code: $errorMessage", Toast.LENGTH_LONG).show()
        Log.e("cek","$code: $errorMessage")
    }

    override fun onTimeout() {
        Toast.makeText(context, R.string.connection_time_out, Toast.LENGTH_LONG).show()
    }

    override fun onNetworkError() {
        Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show()
    }

    override fun showEmpty( message: String?) {
        if(message==null) {
            Toast.makeText(context,R.string.oops_we_cannot_find_the_data , Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,message , Toast.LENGTH_LONG).show()
        }

    }
}