package com.rzhd.poi.presentation.auth

import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<AuthViewModel>() {

    override val dataBindingVariable = BR.vmAuth
    override val layoutId = R.layout.fragment_auth
    override val viewModel: AuthViewModel by viewModel()
}