package com.yux.itfaqcurrency.model.network

import com.yux.itfaqcurrency.model.entities.BaseValueWithList
import com.yux.itfaqcurrency.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseValueAPI {


    @GET(Constants.API_ENDPOINT_LATEST)
    fun getBaseValueList(
        @Query(Constants.API_KEY) access_key: String,
        @Query(Constants.API_SYMBOLS) symbols: String

    ): Single<BaseValueWithList>
}
