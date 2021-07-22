package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class ResellerStock (
    var ResellerID : String ? =null,
    var Gas_state : String ? =null,
    var Regular_Prima : String ? =null,
    var Regular_Kamakhya : String ? =null,
    var Regular_Suvidha : String ? =null,
    var Regular_Others : String ? =null,
    var Leak_Prima : String?= null,
    var Leak_Kamakhya : String?= null,
    var Leak_Suvidha : String?= null,
    var Leak_Others : String?= null,
    var Sold_Prima : String?= null,
    var Sold_Kamakhya : String?= null,
    var Sold_Suvidha : String?= null,
    var Sold_Others : String?= null,
    var SendOrReceive : String?= null,
    var Amount : String?= null,
    var Remarks : String?= null,
        ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(ResellerID)
        parcel.writeString(Gas_state)
        parcel.writeString(Regular_Prima)
        parcel.writeString(Regular_Kamakhya)
        parcel.writeString(Regular_Suvidha)
        parcel.writeString(Regular_Others)
        parcel.writeString(Leak_Prima)
        parcel.writeString(Leak_Kamakhya)
        parcel.writeString(Leak_Suvidha)
        parcel.writeString(Leak_Others)
        parcel.writeString(Sold_Prima)
        parcel.writeString(Sold_Kamakhya)
        parcel.writeString(Sold_Suvidha)
        parcel.writeString(Sold_Others)
        parcel.writeString(SendOrReceive)
        parcel.writeString(Amount)
        parcel.writeString(Remarks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResellerStock> {
        override fun createFromParcel(parcel: Parcel): ResellerStock {
            return ResellerStock(parcel)
        }

        override fun newArray(size: Int): Array<ResellerStock?> {
            return arrayOfNulls(size)
        }
    }
}