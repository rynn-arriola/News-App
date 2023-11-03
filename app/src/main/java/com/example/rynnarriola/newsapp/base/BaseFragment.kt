package com.example.rynnarriola.newsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.di.components.DaggerFragmentComponent
import com.example.rynnarriola.newsapp.di.components.FragmentComponent
import com.example.rynnarriola.newsapp.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(buildFragmentComponent())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    private fun buildFragmentComponent() = DaggerFragmentComponent
        .builder()
        .applicationComponent((requireContext().applicationContext as NewsApplication).applicationComponent)
        .fragmentModule(FragmentModule(this))
        .build()

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
