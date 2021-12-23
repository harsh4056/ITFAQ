package com.yux.itfaqcurrency.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yux.itfaqcurrency.model.entities.BaseValueWithList
import com.yux.itfaqcurrency.model.network.BaseValueAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class BaseValueViewModel : ViewModel() {
    private val baseValueAPIService = BaseValueAPIService()
    private val compositeDisposable = CompositeDisposable()
    val loadBaseValue = MutableLiveData<Boolean>()
    val baseValueWithListResponse = MutableLiveData<BaseValueWithList>()
    val baseValueLoadingError = MutableLiveData<Boolean>()


    fun getBaseValueWithListFromAPI() {
        loadBaseValue.value = true
        compositeDisposable.add(
            baseValueAPIService.getBaseValueWithList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BaseValueWithList>() {
                    override fun onSuccess(baseValueWithList: BaseValueWithList) {
                        loadBaseValue.value = false
                        baseValueWithListResponse.value = baseValueWithList
                        baseValueLoadingError.value = false

                    }

                    override fun onError(e: Throwable) {
                        loadBaseValue.value = false
                        baseValueLoadingError.value = true
                        e.printStackTrace()
                    }
                }
                )
        )
    }


}