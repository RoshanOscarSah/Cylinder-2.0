package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class Company (
    var _id : String ? = null,
    var company_fullname : String? = null,
    var cylinder_name  :String?=null,
    var address : String ? =null,
    var phone_number : String?=null,
    var isActive : String ? =null,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(company_fullname)
        parcel.writeString(cylinder_name)
        parcel.writeString(address)
        parcel.writeString(phone_number)
        parcel.writeString(isActive)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Company> {
        override fun createFromParcel(parcel: Parcel): Company {
            return Company(parcel)
        }

        override fun newArray(size: Int): Array<Company?> {
            return arrayOfNulls(size)
        }
    }
}