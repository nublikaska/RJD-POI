package com.rzhd.poi.presentation.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import com.rzhd.poi.BR
import com.rzhd.poi.R
import com.rzhd.poi.core.ui.BaseFragment
import com.rzhd.poi.domain.AUTH_REQ_CODE
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<AuthViewModel>() {

    override val dataBindingVariable = BR.vmAuth
    override val layoutId = R.layout.fragment_auth
    override val viewModel: AuthViewModel by viewModel()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTH_REQ_CODE && resultCode == RESULT_OK) {

            //todo nav to main
        }
    }
}