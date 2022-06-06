package com.tsvilla.optimus.presentation.fragment


import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tsvilla.optimus.R
import com.tsvilla.optimus.databinding.FragmentHomeBinding
import com.tsvilla.optimus.presentation.model.HomeState
import com.tsvilla.optimus.presentation.viewmodel.HomeViewModel
import com.tsvilla.optimus.utils.verifyAvailableNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<
            HomeState,
            HomeViewModel,
            FragmentHomeBinding>(R.layout.fragment_home) {

    val args: HomeFragmentArgs by navArgs()

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

        if(args.connectionLost) {
            binding.btnStart.visibility = View.GONE
            binding.alert.visibility = View.VISIBLE
        }

        binding.btnStart.setOnClickListener {
            navController.navigate(R.id.nav_monitor)
        }
    }

    override fun updateView(state: HomeState) {

    }

    private fun checkConnection() {
        CoroutineScope(IO).launch {
            delay(2000)
            CoroutineScope(Main).launch {
                if (requireContext().verifyAvailableNetwork()) {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.alert.visibility = View.GONE
                    return@launch
                }
                checkConnection()
            }
        }
    }

}