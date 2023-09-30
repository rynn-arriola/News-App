package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.adapter.LanguageNewsAdapter
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.databinding.FragmentLanguageNewsBinding
import com.example.rynnarriola.newsapp.di.components.DaggerFragmentComponent
import com.example.rynnarriola.newsapp.di.modules.FragmentModule
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.viewmodel.LanguageViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageNewsFragment: Fragment() {

    private var _binding: FragmentLanguageNewsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LanguageViewModel> { viewModelFactory }

    @Inject
    lateinit var adapter : LanguageNewsAdapter

    private val args: LanguageNewsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        viewModel.fetchNews(args.languageCode)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLanguageNewsBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

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
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<LanguageSource>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
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