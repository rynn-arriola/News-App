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
import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.databinding.FragmentLanguageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagesFragment : BaseFragment<BaseViewModel, FragmentLanguageBinding>() {

    private val countriesAdapter by lazy { CountriesAdapter(::selectedCountry) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLanguageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
        }
        val countryArray = resources.getStringArray(R.array.language_list)

        val languageList = countryArray.mapNotNull { item ->
            val cleanedItem = item.replace(" ", "")
            cleanedItem.split(",").takeIf { it.size == 2 }?.let { (code, name) ->
                Country(name, code.trim())
            }
        }.toMutableList()

        countriesAdapter.submitList(languageList)
    }

    private fun selectedCountry(country: Country) {
        val action = LanguagesFragmentDirections
            .actionLanguagesFragmentToLanguageNewsFragment(languageCode = country.code)
        findNavController().navigate(action)
    }
}