package com.tsvilla.optimus.presentation.fragment


import android.graphics.Color
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.fragment.findNavController
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentMonitorBinding
import com.tsvilla.optimus.presentation.model.MonitorState
import com.tsvilla.optimus.presentation.viewmodel.MonitorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonitorFragment :
    BaseFragment<
            MonitorState,
            MonitorViewModel,
            FragmentMonitorBinding>(R.layout.fragment_monitor) {


    override val viewModel: MonitorViewModel by viewModel()

    private lateinit var series: LineGraphSeries<DataPoint>
    private var currentPosX: Double = 0.0

    override fun initView() {

        val animation = AlphaAnimation(0.5f, 0f)
        with (animation) {
            duration = 500
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
            repeatMode = Animation.REVERSE
        }

        binding.heart.startAnimation(animation)

        val graph = binding.graph

        series = LineGraphSeries<DataPoint>()

        graph.addSeries(series)
        graph.gridLabelRenderer.gridStyle = GridLabelRenderer.GridStyle.NONE
        graph.gridLabelRenderer.isHorizontalLabelsVisible = false
        graph.gridLabelRenderer.isVerticalLabelsVisible = false

        val viewport = graph.viewport
        viewport.isYAxisBoundsManual = true
        viewport.setMinY(60.0)
        viewport.setMaxY(100.0)
        viewport.isScrollable = true

        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.nav_settings)
        }

    }

    private fun showBpm(bpm: Double) {
        currentPosX = currentPosX.plus(1.0)


        val point = DataPoint(currentPosX, bpm)
        series.appendData(point, true, 10)
        series.color = getColor(requireContext(), R.color.red)
        binding.graph.onDataChanged(true, true)
    }

    override fun updateView(state: MonitorState) {
        with(binding) {
            bpm.text = state.currentBPM.toString()
            if (state.currentBPM < 60) {
                showBpm(60.0)
            } else if (state.currentBPM > 100) {
                showBpm(100.0)
            } else {
                showBpm(state.currentBPM.toDouble())
            }
        }
    }

}