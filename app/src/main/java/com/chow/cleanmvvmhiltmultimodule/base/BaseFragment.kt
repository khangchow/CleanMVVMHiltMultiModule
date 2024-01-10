package com.chow.cleanmvvmhiltmultimodule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, vm : ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract val bindingInflater: ((LayoutInflater, ViewGroup?, Boolean) -> VB)?
    abstract val viewModel: vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater?.invoke(inflater, container, false)
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