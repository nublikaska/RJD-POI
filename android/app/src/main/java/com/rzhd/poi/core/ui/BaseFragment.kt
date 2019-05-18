package com.rzhd.poi.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rzhd.poi.core.extension.observeNotNull
import com.rzhd.poi.core.vm.BaseViewModel

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    protected abstract val dataBindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutId: Int

    abstract val viewModel: T

    private lateinit var binding: ViewDataBinding

    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let(this::restoreState)

        initViews(savedInstanceState)
    }

    protected open fun initViews(savedInstanceState: Bundle?) {

        initViewModel()

        subscribeBaseViewModelEvents()

        lifecycle.addObserver(viewModel)
    }

    final override fun onSaveInstanceState(outState: Bundle) {

        val bundle = saveState(outState)

        super.onSaveInstanceState(bundle)
    }

    @CallSuper
    protected open fun saveState(bundle: Bundle): Bundle = bundle

    @CallSuper
    protected open fun restoreState(bundle: Bundle) = Unit

    private fun initViewModel() {

        binding.setVariable(dataBindingVariable, viewModel)
        binding.lifecycleOwner = this
    }

    private fun subscribeBaseViewModelEvents() {

        observeNotNull(viewModel.events) { event -> event.execute(this) }
    }
}