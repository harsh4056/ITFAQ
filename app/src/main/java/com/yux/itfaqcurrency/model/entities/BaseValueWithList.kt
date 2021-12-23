package com.yux.itfaqcurrency.model.entities


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BaseValueWithList(
    val base: String,
    val date: String,
    val rates: Map<String,Double>,
    val success: Boolean,
    val timestamp: Int
) : Parcelable
@Parcelize
data class Rate(
    var currency:String,
    val value:Double

):Parcelable