package com.tsvilla.optimus.presentation.fragment


import android.os.Bundle
import android.view.View
import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentSettingsBinding
import com.tsvilla.optimus.domain.model.Setting
import com.tsvilla.optimus.domain.model.SettingType
import com.tsvilla.optimus.presentation.adapter.SettingsAdapter
import com.tsvilla.optimus.presentation.model.HomeState
import com.tsvilla.optimus.presentation.model.SettingsState
import com.tsvilla.optimus.presentation.viewmodel.SettingsViewModel
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
                value = false
            ),
            /*
            Setting(
                drawable = R.drawable.ic_timelapse,
                name = getString(R.string.settings_send_interval),
                value = true
            )*/
        )
        binding.settings.adapter = SettingsAdapter(settings) { position ->
            val setting = settings[position]
        }
    }

    override fun updateView(state: SettingsState) {
        with(state) {

        }
    }


}