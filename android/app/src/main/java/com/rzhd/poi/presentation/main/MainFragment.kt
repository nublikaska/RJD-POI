package com.rzhd.poi.presentation.main

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewModel>() {

    override val dataBindingVariable = BR.vmMain
    override val layoutId = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()
}