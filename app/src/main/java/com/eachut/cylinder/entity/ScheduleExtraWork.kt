package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class ScheduleExtraWork(
    val _id: String ? = null,
    val scheduledDate: String? = null,
    val scheduledTime: String? = null,
    val subject: String ? = null,
    val message: String ? = null,
    val acceptedBy: String ? = null,
    val createdAt: String ? = null,
    val scheduledBy: String ? = null,
    val isAccepted: Boolean ? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(scheduledDate)
        parcel.writeString(scheduledTime)
        parcel.writeString(subject)
        parcel.writeString(message)
        parcel.writeString(acceptedBy)
        parcel.writeString(createdAt)
        parcel.writeString(scheduledBy)
        parcel.writeValue(isAccepted)
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
