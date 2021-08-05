package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class ScheduleExtraWork(
    val _id : String ? = null,
    val scheduledDate : String ? = null,
    val scheduledTime : String ? = null,
    val subject : String ? = null,
    val message : String ? = null,
    val acceptedBy : String ? = null,
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
        parcel.writeString(scheduledDate)
        parcel.writeString(scheduledTime)
        parcel.writeString(subject)
        parcel.writeString(message)
        parcel.writeString(acceptedBy)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScheduleExtraWork> {
        override fun createFromParcel(parcel: Parcel): ScheduleExtraWork {
            return ScheduleExtraWork(parcel)
        }

        override fun newArray(size: Int): Array<ScheduleExtraWork?> {
            return arrayOfNulls(size)
        }
    }
}
