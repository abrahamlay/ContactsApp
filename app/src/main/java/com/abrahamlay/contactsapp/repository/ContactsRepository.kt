package com.abrahamlay.contactsapp.repository

import com.abrahamlay.contactsapp.model.ContactsResponse
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.model.GeneralResponse
import com.abrahamlay.contactsapp.network.CallbackWrapper
import com.abrahamlay.contactsapp.ui.BaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by Abraham on 18/10/2018.
 */
class ContactsRepository(retrofit: Retrofit):ContactsRepoContract{

    private var service=retrofit.create(ContactsAPI::class.java)

    private val compositeDisposable = CompositeDisposable()
    override fun loadAllContacts(view: BaseView, callback: RemoteCallback<ContactsResponse>) {

        val disposable= service.loadAllContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CallbackWrapper<ContactsResponse>(view){
                override fun onSuccess(t: ContactsResponse) {
                    callback.onDataLoaded(t)
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun createContact(data: DataItem, view: BaseView, callback: RemoteCallback<GeneralResponse>) {
        val disposable= service.createContact(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CallbackWrapper<GeneralResponse>(view){
                override fun onSuccess(t: GeneralResponse) {
                    callback.onDataLoaded(t)
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun editContact(id: String?, data: DataItem, view: BaseView, callback: RemoteCallback<GeneralResponse>) {
        val disposable= service.editContact(data,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CallbackWrapper<GeneralResponse>(view){
                override fun onSuccess(t: GeneralResponse) {
                    callback.onDataLoaded(t)
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun deleteContact(id: String?, view: BaseView, callback: RemoteCallback<GeneralResponse>) {
        val disposable= service.deleteContact(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CallbackWrapper<GeneralResponse>(view){
                override fun onSuccess(t: GeneralResponse) {
                    callback.onDataLoaded(t)
                }
            })
        compositeDisposable.add(disposable)
    }

    override fun loadContact(id: String, view: BaseView, callback: RemoteCallback<GeneralResponse>) {
        val disposable= service.getContactById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CallbackWrapper<GeneralResponse>(view){
                override fun onSuccess(t: GeneralResponse) {
                    callback.onDataLoaded(t)
                }
            })
        compositeDisposable.add(disposable)
    }

}