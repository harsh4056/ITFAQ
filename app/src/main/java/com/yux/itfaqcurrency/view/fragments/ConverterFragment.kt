package com.yux.itfaqcurrency.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.yux.itfaqcurrency.R
import com.yux.itfaqcurrency.databinding.FragmentConverterBinding
import com.yux.itfaqcurrency.utils.Utils
import java.text.DecimalFormat

class ConverterFragment : Fragment(), TextWatcher {
    private lateinit var pattern: Regex
    val dec = DecimalFormat("#.##")
    private var mBaseValue: Float = 0.0f
    private lateinit var countryCurrency: String
    private lateinit var mBinding: FragmentConverterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentConverterBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ConverterFragmentArgs by navArgs()
        //Regex matches data greater than equal to 0 all other is rejected
        pattern = Regex("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)\$")
        args.let {
            mBaseValue = it.baseValue
            countryCurrency = it.country
            mBinding.textViewCurrency.text = countryCurrency
            mBinding.editTextEURvalue.addTextChangedListener(this)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //Not required
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        mBinding.editTextEURvalue.error = null
        val inputFromET: String = p0.toString()
        when {
            inputFromET.isEmpty() -> {
                mBinding.textViewValue.setText(resources.getString(R.string.empty))
                mBinding.editTextEURvalue.error = resources.getString(R.string.empty_message)
            }
            pattern.containsMatchIn(inputFromET) -> {
                val currencyInput: Float = inputFromET.toFloat()
                val convertedCurrency=Utils.formatTO2Digits(mBaseValue * currencyInput)
                mBinding.textViewValue.setText(convertedCurrency)
            }
            else -> {
                mBinding.textViewValue.setText(resources.getString(R.string.incorrect))
                mBinding.editTextEURvalue.error = resources.getString(R.string.invalid_message)
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        //Not required
    }


}