package com.tsvilla.optimus.presentation.fragment


import com.tsvilla.optimus.R
import com.tsvilla.optimus.presentation.model.MonitorState
import com.tsvilla.optimus.presentation.viewmodel.MonitorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<
            MonitorState,
            MonitorViewModel,
            FragmentHomeBinding>(R.layout.fragment_home) {


    override val viewModel: HomeViewModel by viewModel()

}