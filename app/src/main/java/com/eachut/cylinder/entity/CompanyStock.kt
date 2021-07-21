package com.eachut.cylinder.entity

data class CompanyStock (
   var _id :String?=null,
   var CompanyID : String ? =null,
   var Gas_state : String ? =null,
   var Regular_Prima : Number ? =null,
   var Regular_Kamakhya : Number ? =null,
   var Regular_Suvidha : Number ? =null,
   var Regular_Others : Number ? =null,
   var Leak_Prima : Number?= null,
   var Leak_Kamakhya : Number?= null,
   var Leak_Suvidha : Number?= null,
   var Leak_Others : Number?= null,
   var Sold_Prima : Number?= null,
   var Sold_Kamakhya : Number?= null,
   var Sold_Suvidha : Number?= null,
   var Sold_Others : Number?= null,
   var SendOrReceive : String?= null,
   var Amount : String?= null,
   var Remarks : String?= null,
   var StockAddedAt : String?= null,
   var Entryby : String?= null,

        )