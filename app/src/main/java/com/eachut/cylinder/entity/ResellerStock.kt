package com.eachut.cylinder.entity

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
        )