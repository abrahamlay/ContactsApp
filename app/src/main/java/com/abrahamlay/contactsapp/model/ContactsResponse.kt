package com.abrahamlay.contactsapp.model


data class ContactsResponse(
    val data: List<DataItem>,
    val message: String? = null
)
