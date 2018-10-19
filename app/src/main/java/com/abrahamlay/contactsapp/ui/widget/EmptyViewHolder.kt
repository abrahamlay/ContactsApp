package com.abrahamlay.contactsapp.ui.widget

import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.abrahamlay.contactsapp.R

/**
 * Created by Abraham on 18/10/2018.
 */
class EmptyViewHolder(private val rootView: View) {
    private val tvMessage: TextView = rootView.findViewById(R.id.tv_message)
    private val icon: ImageView = rootView.findViewById(R.id.iv_empty)

    fun showOnNetworkError() {
        show()
        setMessage(R.string.network_error)
    }

    fun showOnTimeout() {
        show()
        setMessage(R.string.timeout_error)
    }

    fun setEmptyResult() {
        show()
        setMessage(R.string.oops_we_cannot_find_the_data)
    }

    fun setMessage(text: String?): EmptyViewHolder {
        if (text != null) tvMessage.text = text
        return this
    }

    fun setMessage(text: Spanned?): EmptyViewHolder {
        if (text != null) tvMessage.text = text
        return this
    }

    fun setMessage(textResId: Int): EmptyViewHolder {
        tvMessage.setText(textResId)
        return this
    }

    fun setIcon(iconResId: Int): EmptyViewHolder {
        if (iconResId != 0) {
            icon.visibility = View.VISIBLE
            icon.setImageResource(iconResId)
        }

        return this
    }

    fun showIcon(show: Boolean): EmptyViewHolder {
        icon.visibility = if (show) View.VISIBLE else View.GONE
        return this
    }

    fun hide() {
        rootView.visibility = View.GONE
    }

    fun show() {
        rootView.visibility = View.VISIBLE
    }

}