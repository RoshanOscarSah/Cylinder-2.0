package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class NotificationHistory(
    var _id : String ? = null,
    var Title : String ? = null,
    var L1 : String ? = null,
    var L2 : String ? = null,
    var L3 : String ? = null,
    var R1 : String ? = null,
    var R2 : String ? = null,
    var Action : String ? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(Title)
        parcel.writeString(L1)
        parcel.writeString(L2)
        parcel.writeString(L3)
        parcel.writeString(R1)
        parcel.writeString(R2)
        parcel.writeString(Action)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NotificationHistory> {
        override fun createFromParcel(parcel: Parcel): NotificationHistory {
            return NotificationHistory(parcel)
        }

        override fun newArray(size: Int): Array<NotificationHistory?> {
            return arrayOfNulls(size)
        }
    }
}
