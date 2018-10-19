package com.abrahamlay.contactsapp.ui.main

import com.abrahamlay.contactsapp.model.ContactsResponse
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.model.GeneralResponse
import com.abrahamlay.contactsapp.repository.ContactsRepository
import com.abrahamlay.contactsapp.repository.RemoteCallback

/**
 * Created by Abraham on 18/10/2018.
 */
class MainPresenter(private val repository: ContactsRepository,val view :MainContract.MainView):MainContract.MainAction{

    override fun createContact(dataItem: DataItem) {
        view.showLoading(true)
        repository.createContact(dataItem,view,object : RemoteCallback<GeneralResponse> {
            override fun onDataLoaded(data: GeneralResponse) {
                view.onContactCreated(data.message)
                view.showLoading(false)
            }
        })
    }

    override fun loadAllContacts() {
        view.showLoading(true)
        repository.loadAllContacts(view,object : RemoteCallback<ContactsResponse> {
            override fun onDataLoaded(data: ContactsResponse) {
                view.onContactLoaded(data.data)
                view.showLoading(false)
            }
        })
    }

    override fun deleteContact(id: String?) {
        view.showLoading(true)
        repository.deleteContact(id,view,object : RemoteCallback<GeneralResponse> {
            override fun onDataLoaded(data: GeneralResponse) {
                view.onContactDeleted(data.message)
                view.showLoading(false)
            }
        })
    }
}