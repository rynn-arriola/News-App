package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rynnarriola.newsapp.databinding.FragmentNewsSourceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourcesFragment : Fragment() {

    private var _binding: FragmentNewsSourceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentNewsSourceBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bloombergButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "bloomberg")
            findNavController().navigate(action)
        }

        binding.wallStreetButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "the-wall-street-journal")
            findNavController().navigate(action)
        }

        binding.nbcButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "nbc-news")
            findNavController().navigate(action)
        }

        binding.cnnButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "cnn")
            findNavController().navigate(action)
        }

        binding.usaTodayButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "usa-today")
            findNavController().navigate(action)
        }

        binding.reutersButton.setOnClickListener {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "reuters")
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}