package com.za.cocomo

data class rv_model(
    var driver:String,
    var Vlow: Double?,
    var low: Double?,
    var normal:Double,
    var high:Double,
    var Vhigh: Double?,
    var selectedCheckboxIndex: Int? = null,
    var calculatedProduct: Double? = null

)