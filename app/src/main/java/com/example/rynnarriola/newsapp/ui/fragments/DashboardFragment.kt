package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rynnarriola.newsapp.R
import com.example.rynnarriola.newsapp.adapter.TopHeadLinesAdapter
import com.example.rynnarriola.newsapp.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topHeadLinesButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_topHeadLinesFragment)
        }
        binding.newsSourcesButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_newsSourcesFragment)
        }
        binding.countriesButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_countriesFragment)
        }
        binding.languagesButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_languagesFragment)
        }
        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_searchFragment)
        }
    }
}