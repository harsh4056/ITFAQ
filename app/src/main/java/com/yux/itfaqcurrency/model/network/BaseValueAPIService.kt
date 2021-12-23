package com.yux.itfaqcurrency.model.network

import com.yux.itfaqcurrency.model.entities.BaseValueWithList
import com.yux.itfaqcurrency.utils.Constants
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaseValueAPIService {
    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    private val baseValueAPI = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
        .create(BaseValueAPI::class.java)


    fun getBaseValueWithList(): Single<BaseValueWithList> {
        return baseValueAPI.getBaseValueList(
            Constants.API_KEY_VALUE,
            Constants.API_SYMBOLS_VALUE
        )
    }
}