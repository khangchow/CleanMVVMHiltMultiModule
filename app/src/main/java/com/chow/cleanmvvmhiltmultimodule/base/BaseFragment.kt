package com.chow.cleanmvvmhiltmultimodule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, vm : ViewModel> : Fragment() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: ((LayoutInflater, ViewGroup?, Boolean) -> VB)?
    abstract val viewModel: vm

    @Suppress("UNCHECKED_CAST")
    protected fun <BINDING : ViewBinding> binding(): BINDING {
        return _binding as BINDING
    }

    protected fun setBinding(binding: ViewBinding?) {
        _binding = binding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setBinding(bindingInflater?.invoke(inflater, container, false))
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindComponent()
        bindEvent()
        bindData()
        observeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun initData()
    abstract fun bindData()
    abstract fun bindComponent()
    abstract fun bindEvent()

    abstract fun observeData()
}