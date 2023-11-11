package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.adapter.TopHeadLinesAdapter
import com.example.rynnarriola.newsapp.base.BaseFragment
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.databinding.FragmentCountriesNewsBinding
import com.example.rynnarriola.newsapp.di.components.FragmentComponent
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.CountriesNewsViewModel
import javax.inject.Inject

class CountriesNewsFragment : BaseFragment<CountriesNewsViewModel, FragmentCountriesNewsBinding>() {

    private val viewModel by viewModels<CountriesNewsViewModel> { viewModelFactory }

    @Inject
    lateinit var adapter: TopHeadLinesAdapter

    private val args: CountriesNewsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchNews(args.countryCode)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCountriesNewsBinding {
        return FragmentCountriesNewsBinding.inflate(inflater, container, false)
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        return fragmentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        binding.errorLayout.retryButton.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.fetchNews(args.countryCode)
        }
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.errorLayout.root.visibility = View.GONE
                    renderList(uiState.data)
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


    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }
}