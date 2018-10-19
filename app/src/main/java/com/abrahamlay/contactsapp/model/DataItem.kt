package com.abrahamlay.contactsapp.model

import android.os.Parcel
import android.os.Parcelable

data class DataItem(
	var firstName: String? = null,
	var lastName: String? = null,
	var photo: String? = null,
	var id: String? = null,
	var age: Int? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	)
	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(firstName)
		parcel.writeString(lastName)
		parcel.writeString(photo)
		parcel.writeString(id)
		parcel.writeValue(age)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<DataItem> {
		override fun createFromParcel(parcel: Parcel): DataItem {
			return DataItem(parcel)
		}

		override fun newArray(size: Int): Array<DataItem?> {
			return arrayOfNulls(size)
		}
	}
}
