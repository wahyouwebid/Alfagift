package com.ujangwahyu.app.features.categories.presentation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ujangwahyu.app.R
import com.ujangwahyu.app.common.base.BaseFragment
import com.ujangwahyu.app.databinding.FragmentCategoriesBinding
import com.ujangwahyu.app.features.categories.domain.model.CategoriesItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment: BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    private val viewModel : CategoriesViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) = with(binding) {
        toolbar.setToolbar(getString(R.string.app_name))
        search.setOnClickListener { goToSearch()  }
    }

    override fun setupViewModel() {
        viewModel.getCategories(requireContext())
        viewModel.categories.observe(viewLifecycleOwner) {
            onSuccess(it)
        }
    }

    private fun onSuccess(dataList: List<CategoriesItem>) = with(binding) {
        val adapter = CategoriesAdapter { goToSource(it) }
        rvCategories.adapter = adapter
        rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter.setData(dataList)
    }

    private fun goToSource(data: CategoriesItem) {
        navigation?.navigate(
            R.id.action_categoriesFragment_to_sourceFragment,
            bundleOf("data" to data)
        )
    }

    private fun goToSearch() {
        navigation?.navigate(
            R.id.action_categoriesFragment_to_searchFragment,
        )
    }
}