package com.yux.itfaqcurrency.utils

import java.text.DecimalFormat

object Utils {
    val dec = DecimalFormat("#.##")
    fun StringToFloat(s: String): Float {
        return s.toFloat()
    }

    fun formatTO2Digits(f: Float): String {
        return dec.format(f)
    }
}