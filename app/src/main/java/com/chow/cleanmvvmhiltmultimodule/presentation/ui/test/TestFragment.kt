package com.chow.cleanmvvmhiltmultimodule.presentation.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.chow.cleanmvvmhiltmultimodule.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestFragment : Fragment() {
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.menu_fragment_tasks, menu)
//                val searchItem = menu.findItem(R.id.action_search)
//                searchView = searchItem.actionView as SearchView
//                val pendingQuery = viewModel.searchQuery.value
//                searchView.apply {
//                    if (pendingQuery.isNullOrBlank().not()) {
//                        searchItem.expandActionView()
//                        setQuery(pendingQuery, false)
//                    }
//                    onQueryTextChanged {
//                        viewModel.onQueryTextChanged(it)
//                    }
//                    lifecycleScope.launch {
//                        menu.findItem(R.id.action_hide_completed_tasks).isChecked =
//                            viewModel.preferencesFlow.first().hideCompleted
//                    }
//                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
//                    R.id.action_sort_by_name -> {
//                        viewModel.onSortOrderSelected(SortOrder.BY_NAME)
//                        true
//                    }
//                    R.id.action_hide_completed_tasks -> {
//                        menuItem.isChecked = !menuItem.isChecked
//                        viewModel.onHideCompleted(menuItem.isChecked)
//                        true
//                    }
                    else -> false
                }
            }
        }
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.setOnQueryTextListener(null)
    }
}