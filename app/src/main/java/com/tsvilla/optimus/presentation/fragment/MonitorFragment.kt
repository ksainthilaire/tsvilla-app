package com.tsvilla.optimus.presentation.fragment


import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentMonitorBinding
import com.tsvilla.optimus.presentation.model.HomeState
import com.tsvilla.optimus.presentation.model.MonitorState
import com.tsvilla.optimus.presentation.viewmodel.MonitorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonitorFragment :
    BaseFragment<
            MonitorState,
            MonitorViewModel,
            FragmentMonitorBinding>(R.layout.fragment_monitor) {


    override val viewModel: MonitorViewModel by viewModel()



    override fun initView() {

    }

    override fun updateView(state: MonitorState) {

    }

}