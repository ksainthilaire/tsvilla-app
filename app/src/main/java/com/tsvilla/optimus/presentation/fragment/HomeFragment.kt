package com.tsvilla.optimus.presentation.fragment


import android.widget.Toast
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

    override fun initView() {

        with( binding.btnStart) {
            alpha = 0f
            translationY = 50F

            animate()
                .alpha(1f)
                .translationYBy(-50f)
                .setDuration(1000)
        }




        binding.btnStart.setOnClickListener {
            navController.navigate(R.id.nav_monitor)
        }
    }

    override fun updateView(state: HomeState) {

    }

}