package com.abrahamlay.contactsapp.repository

import com.abrahamlay.contactsapp.model.ContactsResponse
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.model.GeneralResponse
import com.abrahamlay.contactsapp.ui.BaseView

/**
 * Created by Abraham on 18/10/2018.
 */
interface ContactsRepoContract{
    fun createContact(data: DataItem, view: BaseView, callback: RemoteCallback<GeneralResponse>)
    fun editContact(id:String ?, data: DataItem, view: BaseView, callback: RemoteCallback<GeneralResponse>)
    fun deleteContact(id:String? ,view: BaseView, callback: RemoteCallback<GeneralResponse>)
    fun loadAllContacts(view: BaseView, callback: RemoteCallback<ContactsResponse>)
    fun loadContact(id:String, view: BaseView, callback: RemoteCallback<GeneralResponse>)
}