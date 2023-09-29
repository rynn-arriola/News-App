package com.example.rynnarriola.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rynnarriola.newsapp.databinding.FragmentNewsSourceBinding

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
}