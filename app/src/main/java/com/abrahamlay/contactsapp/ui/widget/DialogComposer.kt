package com.abrahamlay.contactsapp.ui.widget

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import com.abrahamlay.contactsapp.R
import com.abrahamlay.contactsapp.model.DataItem

/**
 * Created by Abraham on 19/10/2018.
 */
object DialogComposer{
    fun showWriteDialog(activity: Activity,
                        title:String,
                        message :String,
                        dataItem: DataItem?,
                        saveListener:GeneralListener.OnClickListener) {
        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.write_dialog, null)
        dialogBuilder.setView(dialogView)

        val etFirstName = dialogView.findViewById<View>(R.id.et_first_name) as EditText
        val etLastName = dialogView.findViewById<View>(R.id.et_last_name) as EditText
        val etAge = dialogView.findViewById<View>(R.id.et_age) as EditText
        val etPhoto = dialogView.findViewById<View>(R.id.et_photo_path) as EditText

        dataItem.let {

            etFirstName.setText(it?.firstName)
            etLastName.setText(it?.lastName)
            etAge.setText(it?.age.toString())
            etPhoto.setText(it?.photo)
        }

        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton(activity.getString(R.string.save)) { dialog, whichButton ->
           val data=DataItem()

            data.firstName=etFirstName.text.toString()
            data.lastName=etLastName.text.toString()
            data.age=etAge.text.toString().toInt()
            if(etPhoto.text.toString() == "") {
                data.photo="N/A"
            } else {
                data.photo=etPhoto.text.toString()
            }

            saveListener.onClick(data)

            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton(activity.getString(R.string.cancel)) { dialog, whichButton ->
            dialog.dismiss()
        }
        val b = dialogBuilder.create()
        b.show()
    }

    fun showDeleteDialog(activity: Activity,
                        dataItem: DataItem,
                        deleteListener:GeneralListener.OnClickListener) {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle(activity.getString(R.string.delete))
        dialogBuilder.setMessage("${dataItem.firstName} ${dataItem.lastName}")
        dialogBuilder.setPositiveButton(activity.getString(R.string.delete)) { dialog, whichButton ->

            deleteListener.onClick(dataItem)

            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton(activity.getString(R.string.cancel)) { dialog, whichButton ->
            dialog.dismiss()
        }
        val b = dialogBuilder.create()
        b.show()
    }
}