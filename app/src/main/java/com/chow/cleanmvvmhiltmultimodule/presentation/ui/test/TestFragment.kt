package com.chow.cleanmvvmhiltmultimodule.presentation.ui.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chow.cleanmvvmhiltmultimodule.base.BaseFragment
import com.chow.cleanmvvmhiltmultimodule.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestFragment : BaseFragment<FragmentTestBinding, TestViewModel>() {
    private lateinit var searchView: SearchView
    override val viewModel: TestViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTestBinding
        get() = FragmentTestBinding::inflate
    private val binding by lazy { binding<FragmentTestBinding>() }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val menuHost: MenuHost = requireActivity()
//        val menuProvider = object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
////                menuInflater.inflate(R.menu.menu_fragment_tasks, menu)
////                val searchItem = menu.findItem(R.id.action_search)
////                searchView = searchItem.actionView as SearchView
////                val pendingQuery = viewModel.searchQuery.value
////                searchView.apply {
////                    if (pendingQuery.isNullOrBlank().not()) {
////                        searchItem.expandActionView()
////                        setQuery(pendingQuery, false)
////                    }
////                    onQueryTextChanged {
////                        viewModel.onQueryTextChanged(it)
////                    }
////                    lifecycleScope.launch {
////                        menu.findItem(R.id.action_hide_completed_tasks).isChecked =
////                            viewModel.preferencesFlow.first().hideCompleted
////                    }
////                }
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                return when (menuItem.itemId) {
////                    R.id.action_sort_by_name -> {
////                        viewModel.onSortOrderSelected(SortOrder.BY_NAME)
////                        true
////                    }
////                    R.id.action_hide_completed_tasks -> {
////                        menuItem.isChecked = !menuItem.isChecked
////                        viewModel.onHideCompleted(menuItem.isChecked)
////                        true
////                    }
//                    else -> false
//                }
//            }
//        }
////        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
//
//    }

    override fun initData() {

    }

    override fun bindData() {
        viewModel.navigate()
    }

    override fun bindComponent() {

    }

    override fun bindEvent() {

    }

    override fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {
                    when (it) {
                        is TestEvent.NavigateToHomeScreen -> TODO()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.setOnQueryTextListener(null)
    }
}