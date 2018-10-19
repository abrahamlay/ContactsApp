package com.abrahamlay.contactsapp.ui.widget

import android.content.Context
import android.widget.ImageView
import com.abrahamlay.contactsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Abraham on 19/10/2018.
 */
object GlideHelper{
    fun showImage(context:Context,url:String?, imageView: ImageView){
        val requestOptions = RequestOptions().apply {
            placeholder(R.color.colorPrimary)
            error(R.color.colorPrimary)
        }

        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(url)
            .into(imageView)
    }
}