package com.yux.itfaqcurrency.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yux.itfaqcurrency.databinding.FragmentCurrencySelectionBinding
import com.yux.itfaqcurrency.model.entities.BaseValueWithList
import com.yux.itfaqcurrency.view.adapters.CountryCurrencyAdapter
import com.yux.itfaqcurrency.viewmodel.BaseValueViewModel


class CurrencySelectionFragment : Fragment() {


    private var baseValueWithList: BaseValueWithList? = null
    private lateinit var adapter: CountryCurrencyAdapter
    private lateinit var mBinding: FragmentCurrencySelectionBinding
    private lateinit var mBaseValueViewModel: BaseValueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCurrencySelectionBinding.inflate(inflater, container, false)
        mBinding.swipeRefreshCurrencies.isRefreshing = true
        adapter = CountryCurrencyAdapter(this@CurrencySelectionFragment)
        setAdapter(adapter)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBaseValueViewModel = ViewModelProvider(this)[BaseValueViewModel::class.java]
        mBaseValueViewModel.getBaseValueWithListFromAPI()
        mBinding.swipeRefreshCurrencies.setOnRefreshListener {
            mBinding.swipeRefreshCurrencies.isRefreshing = true
            mBaseValueViewModel.getBaseValueWithListFromAPI()
        }
        registerObservers()
    }

    private fun registerObservers() {
        mBaseValueViewModel.baseValueWithListResponse.observe(
            viewLifecycleOwner,
            { baseValueWithListResponse ->
                baseValueWithListResponse.let {
                    if(it.success)
                    baseValueWithList = it
                    else
                        Toast.makeText(activity,"Your monthly usage limit has been reached. Please upgrade your Subscription Plan.",Toast.LENGTH_SHORT).show()
                }
                mBinding.swipeRefreshCurrencies.isRefreshing = false
                updateAdapter()
            }
        )

        mBaseValueViewModel.baseValueLoadingError.observe(
            viewLifecycleOwner,
            {
                if(it){
                    Toast.makeText(activity,"Api Call failed!",Toast.LENGTH_SHORT).show()
                }

            }

        )

    }

    private fun updateAdapter() = adapter.updateRates(baseValueWithList?.rates)
    private fun setAdapter(adapter: CountryCurrencyAdapter) {
        mBinding.recyclerView.adapter = adapter
    }


}