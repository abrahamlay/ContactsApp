package com.abrahamlay.contactsapp.util

import android.app.Application
import android.content.Context
import com.abrahamlay.contactsapp.network.ApiConfig
import com.abrahamlay.contactsapp.network.RetrofitInstance
import com.abrahamlay.contactsapp.repository.ContactsRepository
import com.abrahamlay.contactsapp.ui.detail.DetailContract
import com.abrahamlay.contactsapp.ui.detail.DetailPresenter
import com.abrahamlay.contactsapp.ui.main.MainContract
import com.abrahamlay.contactsapp.ui.main.MainPresenter
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abraham on 18/10/2018.
 */
class MyApplication: Application(){
    companion object {
        lateinit var instanceApp: MyApplication
    }

    private val myModule = module {

        single { RetrofitInstance(
            ApiConfig.BASE_URL,
            GsonConverterFactory.create(ApiConfig.GSON),
            ApiConfig.rxAdapter).getInstance()}
        single { ContactsRepository(get()) }
        single { (view: MainContract.MainView)->MainPresenter(get(),view) }
        single { (view: DetailContract.DetailView)->DetailPresenter(get(),view) }

    }


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))

        instanceApp = this

    }
}