package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.R
import com.example.rynnarriola.newsapp.adapter.CountriesAdapter
import com.example.rynnarriola.newsapp.base.BaseFragment
import com.example.rynnarriola.newsapp.base.BaseViewModel
import com.example.rynnarriola.newsapp.data.model.Countries
import com.example.rynnarriola.newsapp.databinding.FragmentCountriesBinding
import com.example.rynnarriola.newsapp.di.components.FragmentComponent

class CountriesFragment : BaseFragment<BaseViewModel, FragmentCountriesBinding>() {

    private val countriesAdapter by lazy { CountriesAdapter(::selectedCountry) }
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCountriesBinding {
        return FragmentCountriesBinding.inflate(inflater, container, false)
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
        }
        val countryArray = resources.getStringArray(R.array.countries_list)

        val countryList = countryArray.mapNotNull { item ->
            val cleanedItem = item.replace(" ", "")
            cleanedItem.split(",").takeIf { it.size == 2 }?.let { (name, code) ->
                Countries(name, code.trim())
            }
        }.toMutableList()

        countriesAdapter.submitList(countryList)
    }

    private fun selectedCountry(country: Countries) {
        val action = CountriesFragmentDirections
            .actionCountriesFragmentToCountriesNewsFragment(countryCode = country.code)
        findNavController().navigate(action)
    }
}