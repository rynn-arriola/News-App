package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rynnarriola.newsapp.base.openCustomChromeTab
import com.example.rynnarriola.newsapp.ui.compose.LanguageNewsScreen
import com.example.rynnarriola.newsapp.viewmodel.LanguageNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageNewsFragment : Fragment() {

    private val viewModel by viewModels<LanguageNewsViewModel> ()

    private val args: LanguageNewsFragmentArgs by navArgs()

    private lateinit var composeView: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchNews(args.languageCode)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).also { composeView = it }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        composeView.setContent {
            val uiState by viewModel.uiState.collectAsState()
            LanguageNewsScreen(uiState = uiState , onNewsClick = {
                openCustomChromeTab(requireContext(), it)
            })
        }
    }
}