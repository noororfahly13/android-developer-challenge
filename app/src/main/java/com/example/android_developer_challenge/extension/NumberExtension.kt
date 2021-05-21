package com.example.android_developer_challenge.extension

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.removeFraction(): String {
    val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
    val formatter = nf as DecimalFormat
    //for 10.0 it returns 10
    formatter.applyPattern("0.#")
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 0
    return nf.format(this)
}

fun Float?.format(): String? {
    val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
    val formatter = nf as DecimalFormat
    formatter.applyPattern("#,###,###")
    formatter.maximumFractionDigits = 2
    return nf.format(this)
}

fun Double?.format(): String? {
    val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
    val formatter = nf as DecimalFormat
    formatter.applyPattern("#,###,###")
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2

    return nf.format(this)
}