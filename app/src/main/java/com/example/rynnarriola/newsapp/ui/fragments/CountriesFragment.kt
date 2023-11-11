package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.adapter.CountriesAdapter
import com.example.rynnarriola.newsapp.base.BaseFragment
import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.databinding.FragmentCountriesBinding
import com.example.rynnarriola.newsapp.di.components.FragmentComponent
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.CountriesViewModel
import kotlinx.coroutines.launch

class CountriesFragment : BaseFragment<CountriesViewModel, FragmentCountriesBinding>() {

    private val viewModel by viewModels<CountriesViewModel> { viewModelFactory }

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

        setupObserver()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
        }
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.errorLayout.root.visibility = View.GONE
                            countriesAdapter.submitList(it.data)
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            binding.errorLayout.root.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            binding.errorLayout.root.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun selectedCountry(country: Country) {
        val action = CountriesFragmentDirections
            .actionCountriesFragmentToCountriesNewsFragment(countryCode = country.code)
        findNavController().navigate(action)
    }
}