package com.abrahamlay.contactsapp.ui.detail

import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.ui.BaseView

class DetailContract {
    interface DetailView :BaseView{
        fun onContactEdited(message:String?,data: DataItem)

        fun onContactLoaded(list:DataItem)
    }

    interface DetailAction{
        fun loadContact(id: String)

        fun editContact(id: String?, data: DataItem)
    }


}
