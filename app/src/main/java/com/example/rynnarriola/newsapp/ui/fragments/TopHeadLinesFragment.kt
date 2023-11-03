package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.adapter.TopHeadLinesAdapter
import com.example.rynnarriola.newsapp.base.BaseFragment
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.databinding.FragmentTopHeadlinesBinding
import com.example.rynnarriola.newsapp.di.components.FragmentComponent
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.TopHeadLinesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadLinesFragment : BaseFragment<TopHeadLinesViewModel, FragmentTopHeadlinesBinding>() {

    private val viewModel by viewModels<TopHeadLinesViewModel> { viewModelFactory }

    @Inject
    lateinit var adapter : TopHeadLinesAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentTopHeadlinesBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

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
            viewModel.fetchNews()
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
                            renderList(it.data)
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

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }
}