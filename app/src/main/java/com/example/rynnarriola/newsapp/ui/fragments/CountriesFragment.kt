package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.R
import com.example.rynnarriola.newsapp.adapter.CountriesAdapter
import com.example.rynnarriola.newsapp.data.model.Countries
import com.example.rynnarriola.newsapp.databinding.FragmentCountriesBinding
import com.example.rynnarriola.newsapp.di.components.DaggerFragmentComponent
import com.example.rynnarriola.newsapp.di.modules.FragmentModule

class CountriesFragment : Fragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private val countriesAdapter by lazy { CountriesAdapter(::selectedCountry) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCountriesBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
        }
        val countryArray = resources.getStringArray(R.array.countries_list)

        val countryList = countryArray.mapNotNull { item ->
            item.split(",").takeIf { it.size == 2 }?.let { (name, code) ->
                Countries(name, code)
            }
        }.toMutableList()
        countriesAdapter.submitList(countryList)
    }

    private fun selectedCountry( country: Countries) {

    }

    private fun injectDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as NewsApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }
}