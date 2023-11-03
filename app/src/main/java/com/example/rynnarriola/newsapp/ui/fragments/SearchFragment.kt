package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.example.rynnarriola.newsapp.base.BaseFragment
import com.example.rynnarriola.newsapp.base.BaseViewModel
import com.example.rynnarriola.newsapp.databinding.FragmentSearchBinding
import com.example.rynnarriola.newsapp.di.components.FragmentComponent

class SearchFragment : BaseFragment<BaseViewModel, FragmentSearchBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch(binding.searchEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun performSearch(searchStr: String) {
        val action = SearchFragmentDirections
            .actionSearchFragmentToSearchNewsFragment(query = searchStr)
        findNavController().navigate(action)
    }
}