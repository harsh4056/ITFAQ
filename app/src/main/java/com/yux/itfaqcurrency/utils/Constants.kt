package com.yux.itfaqcurrency.utils

object Constants {

    const val BASE_URL = "http://data.fixer.io/api/"
    const val API_KEY_VALUE: String = "1b0df16461c8d5c767c7d4715b8026b3"
    const val API_ENDPOINT_LATEST:String = "latest"
    const val API_ENDPOINT_CONVERT:String = "convert"
    const val API_KEY: String = "access_key"
    const val API_BASE: String = "base"
    const val API_BASE_VALUE: String = "EUR"
    const val API_SYMBOLS: String = "symbols"
    const val API_SYMBOLS_VALUE: String = "USD,AUD,CAD,PLN,MXN,INR"
    const val API_FROM: String = "from"
    const val API_TO: String = "to"
    const val API_AMOUNT: String = "amount"
    const val API_FROM_VALUE: String = "EUR"
    const val API_TO_VALUE: String = "USD"
    const val API_AMOUNT_VALUE: String = "25"

    /*
    http://data.fixer.io/api/latest

    ? access_key = YOUR_ACCESS_KEY
    & base = GBP
    & symbols = USD,AUD,CAD,PLN,MXN
     */

    /*
    http://data.fixer.io/api/convert

    ? access_key = YOUR_ACCESS_KEY
    & from = USD
    & to = EUR
    & amount = 25
     */



}