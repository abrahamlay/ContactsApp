package com.abrahamlay.contactsapp.ui.main

import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseView

/**
 * Created by Abraham on 18/10/2018.
 */
class MainContract{
    interface MainView:BaseView{
        fun onContactLoaded(testList: List<DataItem>)

        fun onContactCreated(message:String?)

        fun onContactDeleted(message: String?)
    }

    interface MainAction{
        fun loadAllContacts()

        fun createContact(dataItem: DataItem)

        fun deleteContact(id:String?)
    }

}