package com.eachut.cylinder.entity

import android.os.Parcel
import android.os.Parcelable

data class CompanyStock(
   var _id:String?=null,
   var CompanyID: String ? =null,
   var Gas_state: String ? =null,
   var Regular_Prima: Number? =null,
   var Regular_Kamakhya: Number ? =null,
   var Regular_Suvidha: Number ? =null,
   var Regular_Others: Number ? =null,
   var Leak_Prima: Number?= null,
   var Leak_Kamakhya: Number?= null,
   var Leak_Suvidha: Number?= null,
   var Leak_Others: Number?= null,
   var Sold_Prima: Number?= null,
   var Sold_Kamakhya: Number?= null,
   var Sold_Suvidha: Number?= null,
   var Sold_Others: Number?= null,
   var SendOrReceive: String?= null,
   var Amount: String?= null,
   var Remarks: String?= null,
   var StockAddedAt: String?= null,


   ):Parcelable{
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

      ) {
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(_id)
      parcel.writeString(CompanyID)
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
      parcel.writeString(Amount)
      parcel.writeString(Remarks)
      parcel.writeString(StockAddedAt)




   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<CompanyStock> {
      override fun createFromParcel(parcel: Parcel): CompanyStock {
         return CompanyStock(parcel)
      }

      override fun newArray(size: Int): Array<CompanyStock?> {
         return arrayOfNulls(size)
      }
   }

}