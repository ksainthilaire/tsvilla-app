package com.tsvilla.optimus.presentation.fragment

import androidx.navigation.fragment.findNavController
import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentSettingsBinding
import com.tsvilla.optimus.domain.model.Setting
import com.tsvilla.optimus.domain.model.SettingType
import com.tsvilla.optimus.presentation.adapter.SettingsAdapter
import com.tsvilla.optimus.presentation.model.SettingsState
import com.tsvilla.optimus.presentation.viewmodel.SettingsViewModel
import com.tsvilla.optimus.utils.isDarkModeEnabled
import com.tsvilla.optimus.utils.setDarkMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment :
    BaseFragment<
            SettingsState,
            SettingsViewModel,
            FragmentSettingsBinding>(R.layout.fragment_settings) {


    override val viewModel: SettingsViewModel by viewModel()
    override fun initView() {
        val settings = listOf(
            Setting(
                type = SettingType.SWITCH,
                drawable = R.drawable.ic_darkmode,
                name = getString(R.string.settings_dark_mode),
                value = requireContext().isDarkModeEnabled()
            ),/*
            Setting(
                drawable = R.drawable.ic_timelapse,
                name = getString(R.string.settings_send_interval),
                value = true
            )*/
        )

        binding.settings.adapter = SettingsAdapter(settings) { position ->
            val setting = settings[position]
            when (setting.name) {
                getString(R.string.settings_dark_mode) -> {
                    val mode = !setting.value
                    context?.setDarkMode(mode)
                }
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun updateView(state: SettingsState) {
        with(state) {

        }
    }


}