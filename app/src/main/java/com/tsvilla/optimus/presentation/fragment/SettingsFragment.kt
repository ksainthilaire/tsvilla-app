package com.tsvilla.optimus.presentation.fragment


import com.tsvilla.optimus.R
import com.tsvilla.optimus.presentation.model.SettingsState
import com.tsvilla.optimus.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment :
    BaseFragment<
            SettingsState,
            SettingsViewModel,
            SettingsFragmentBinding>(R.layout.fragment_settings) {


    override val viewModel: SettingsViewModel by viewModel()



}