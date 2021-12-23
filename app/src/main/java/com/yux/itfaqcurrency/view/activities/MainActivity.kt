package com.yux.itfaqcurrency.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.yux.itfaqcurrency.R
import com.yux.itfaqcurrency.databinding.ActivityMainBinding


import com.yux.itfaqcurrency.viewmodel.BaseValueViewModel

class MainActivity : AppCompatActivity(),LifecycleOwner {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


    }
}