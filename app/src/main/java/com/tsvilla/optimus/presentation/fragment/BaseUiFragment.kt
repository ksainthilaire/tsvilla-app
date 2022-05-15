package com.tsvilla.optimus.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tsvilla.optimus.presentation.model.BaseState
import com.tsvilla.optimus.presentation.utils.LifecycleChecker
import com.tsvilla.optimus.presentation.viewmodel.BaseViewModel

abstract class BaseFragment<
        State : BaseState,
        ViewModel: BaseViewModel<State>,
        ViewBinding: ViewDataBinding>(private val layoutId: Int) : Fragment() {

    protected val navController: NavController by lazy {
        findNavController()
    }

    abstract val viewModel: ViewModel
    protected lateinit var binding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        initViewModel()
        initView()

        return binding.root
    }

    protected abstract fun updateView(state: State)

    protected abstract fun initView()

    protected fun initViewModel() {
        val disposable = viewModel.state.
        subscribe(::updateView)

        viewLifecycleOwner.lifecycle.addObserver(LifecycleChecker(disposable))
    }

}