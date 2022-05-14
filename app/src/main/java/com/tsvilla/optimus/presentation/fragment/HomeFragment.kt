package com.tsvilla.optimus.presentation.fragment


import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentHomeBinding
import com.tsvilla.optimus.presentation.model.HomeState
import com.tsvilla.optimus.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<
            HomeState,
            HomeViewModel,
            FragmentHomeBinding>(R.layout.fragment_home) {


    override val viewModel: HomeViewModel by viewModel()

}