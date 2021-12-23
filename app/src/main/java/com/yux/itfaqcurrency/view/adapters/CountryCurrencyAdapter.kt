package com.yux.itfaqcurrency.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yux.itfaqcurrency.R
import com.yux.itfaqcurrency.databinding.ItemCurrencyBinding
import com.yux.itfaqcurrency.model.entities.Rate
import com.yux.itfaqcurrency.view.fragments.CurrencySelectionFragment
import com.yux.itfaqcurrency.view.fragments.CurrencySelectionFragmentDirections

class CountryCurrencyAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<CountryCurrencyAdapter.ViewHolder>() {

    private var rates: List<Rate> = listOf()




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemCurrencyBinding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCurrency.text = rates[position].currency
        holder.textViewValue.text = rates[position].value.toString()
        holder.itemView.setOnClickListener {
            if (fragment is CurrencySelectionFragment)
                fragment.findNavController().navigate(
                    CurrencySelectionFragmentDirections.actionNavigationSelectionToConverter(
                        rates[position].value.toFloat(),
                        rates[position].currency
                    )
                )
        }
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    fun updateRates(ratesMap: Map<String, Double>?) {
        val list = arrayListOf<Rate>()
        val map = getFlagsMap()
        if (ratesMap != null) {
            for ((key, value) in ratesMap) {
                val rate = map[key]?.let { currency -> Rate(currency, value) }
                if (rate != null) {
                    list.add(rate)
                }
            }
        }
        rates = list
        notifyDataSetChanged()
    }

    private fun getFlagsMap(): Map<String, String> {
        val map = HashMap<String, String>()
        map["USD"] = fragment.resources.getString(R.string.usd)
        map["AUD"] = fragment.resources.getString(R.string.aud)
        map["CAD"] = fragment.resources.getString(R.string.cad)
        map["MXN"] = fragment.resources.getString(R.string.mxn)
        map["PLN"] = fragment.resources.getString(R.string.pln)
        map["INR"] = fragment.resources.getString(R.string.inr)
        return map

    }

    class ViewHolder(view: ItemCurrencyBinding) : RecyclerView.ViewHolder(view.root) {

        val textViewCurrency = view.textViewCurrency
        val textViewValue = view.textViewValue

    }


}