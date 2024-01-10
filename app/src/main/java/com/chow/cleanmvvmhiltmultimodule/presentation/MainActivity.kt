package com.chow.cleanmvvmhiltmultimodule.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.chow.cleanmvvmhiltmultimodule.R
import com.chow.cleanmvvmhiltmultimodule.base.BaseActivity
import com.chow.cleanmvvmhiltmultimodule.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private lateinit var navController: NavController
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
        binding.navHostFragment
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun bindData() {
        TODO("Not yet implemented")
    }

    override fun bindComponent() {
        TODO("Not yet implemented")
    }

    override fun bindEvent() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }

    override fun setUpNavigation() {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}