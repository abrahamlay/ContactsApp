package com.abrahamlay.contactsapp.ui.detail

import com.abrahamlay.contactsapp.model.ContactsResponse
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.model.GeneralResponse
import com.abrahamlay.contactsapp.repository.ContactsRepository
import com.abrahamlay.contactsapp.repository.RemoteCallback


/**
 * Created by Abraham on 19/10/2018.
 */
class DetailPresenter(private val repository: ContactsRepository, val view :DetailContract.DetailView): DetailContract.DetailAction{

    override fun editContact(id: String?,data: DataItem) {
        view.showLoading(true)
        repository.editContact(id,data,view,object : RemoteCallback<GeneralResponse> {
            override fun onDataLoaded(data: GeneralResponse) {
                view.onContactEdited(data.message,data.data)
                view.showLoading(false)
            }
        })
    }

    override fun loadContact(id:String) {
        view.showLoading(true)
        repository.loadContact(id,view,object : RemoteCallback<GeneralResponse> {
            override fun onDataLoaded(data: GeneralResponse) {
                view.onContactLoaded(data.data)
                view.showLoading(false)
            }
        })
    }
}