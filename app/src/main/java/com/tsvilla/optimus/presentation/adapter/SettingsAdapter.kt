package com.tsvilla.optimus.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.tsvilla.optimus.R
import com.tsvilla.optimus.domain.model.Setting
import com.tsvilla.optimus.domain.model.SettingType


class SettingsAdapter(
    private var settings: List<Setting>,
    private val listener: (position: Int) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    inner class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val btn: Button = view.findViewById(R.id.btn)
        val switch: SwitchCompat = view.findViewById(R.id.switch_view)

        init {
            switch.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.invoke(absoluteAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.settings_item, parent, false)

        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val setting = settings[position]
        with(holder) {
            when (setting.type) {
                SettingType.SWITCH -> {
                    switch.visibility = VISIBLE
                    switch.text = setting.name
                    switch.isChecked = setting.value
                }
                else -> {
                    btn.visibility = VISIBLE
                    btn.text = setting.name
                    btn.setCompoundDrawablesWithIntrinsicBounds(setting.drawable, 0, 0, 0)
                }
            }
        }
    }

    override fun getItemCount() = settings.size
}