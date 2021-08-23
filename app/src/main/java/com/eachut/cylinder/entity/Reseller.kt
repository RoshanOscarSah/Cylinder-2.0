package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Reseller (
    var _id : String ? = null,
    var reseller_fullname : String? = null,
    var pasal_name  :String?=null,
    var address  :String?=null,
    var phone_number  :String?=null,
    var rateforReseller  :String?=null

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(reseller_fullname)
        parcel.writeString(pasal_name)
        parcel.writeString(address)
        parcel.writeString(phone_number)
        parcel.writeString(rateforReseller)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Reseller> {
        override fun createFromParcel(parcel: Parcel): Reseller {
            return Reseller(parcel)
        }

        override fun newArray(size: Int): Array<Reseller?> {
            return arrayOfNulls(size)
        }
    }
}