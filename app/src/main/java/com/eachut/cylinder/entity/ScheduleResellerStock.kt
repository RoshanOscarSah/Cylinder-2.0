package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class ScheduleResellerStock(
    val _id : String ? = null,
    val ResellerID: String ? = null,
    var Gas_state: String ? =null,
    val Regular_Prima:  Number ? = null,
    val Regular_Kamakhya: Number ? = null,
    val Regular_Suvidha: Number ? = null,
    val Regular_Others: Number ? = null,
    val Leak_Prima: Number ? = null,
    val Leak_Kamakhya: Number ? = null,
    val Leak_Suvidha: Number ? = null,
    val Leak_Others: Number ? = null,
    val Sold_Prima: Number ? = null,
    val Sold_Kamakhya: Number ? = null,
    val Sold_Suvidha: Number ? = null,
    val Sold_Others: Number ? = null,
    val SendOrReceive: String ? = null,
    val scheduledDate: String ? = null,
    val scheduledTime: String ? = null,
    val Remarks: String ? = null,
    val Entryby: String ? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(ResellerID)
        parcel.writeString(Gas_state)
        parcel.writeInt(Regular_Prima as Int)
        parcel.writeInt(Regular_Kamakhya as Int)
        parcel.writeInt(Regular_Suvidha as Int)
        parcel.writeInt(Regular_Others as Int)
        parcel.writeInt(Leak_Prima as Int)
        parcel.writeInt(Leak_Kamakhya as Int)
        parcel.writeInt(Leak_Suvidha as Int)
        parcel.writeInt(Leak_Others as Int)
        parcel.writeInt(Sold_Prima as Int)
        parcel.writeInt(Sold_Kamakhya as Int)
        parcel.writeInt(Sold_Suvidha as Int)
        parcel.writeInt(Sold_Others as Int)
        parcel.writeString(SendOrReceive)
        parcel.writeString(scheduledDate)
        parcel.writeString(scheduledTime)
        parcel.writeString(Remarks)
        parcel.writeString(Entryby)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScheduleResellerStock> {
        override fun createFromParcel(parcel: Parcel): ScheduleResellerStock {
            return ScheduleResellerStock(parcel)
        }

        override fun newArray(size: Int): Array<ScheduleResellerStock?> {
            return arrayOfNulls(size)
        }
    }
}