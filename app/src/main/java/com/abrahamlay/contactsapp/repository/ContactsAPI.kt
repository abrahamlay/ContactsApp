package com.abrahamlay.contactsapp.repository

import com.abrahamlay.contactsapp.model.ContactsResponse
import com.abrahamlay.contactsapp.model.DataItem
import com.abrahamlay.contactsapp.model.GeneralResponse
import com.abrahamlay.contactsapp.network.ApiConfig
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Abraham on 18/10/2018.
 */
interface ContactsAPI{
    @POST(ApiConfig.CONTACT_URL)
    fun createContact(@Body data: DataItem): Observable<GeneralResponse>

    @PUT(ApiConfig.CONTACT_WITH_ID_URL)
    fun editContact(@Body data: DataItem,@Path("id") id:String?): Observable<GeneralResponse>

    @DELETE(ApiConfig.CONTACT_WITH_ID_URL)
    fun deleteContact(@Path("id") id:String?): Observable<GeneralResponse>

    @DELETE(ApiConfig.CONTACT_WITH_ID_URL)
    fun getContactById(@Path("id") id:String?): Observable<GeneralResponse>

    @GET(ApiConfig.CONTACT_URL)
    fun loadAllContacts(): Observable<ContactsResponse>
}