package com.rzhd.poi.presentation.auth

import com.rzhd.poi.core.event.ViewEventStartAuth
import com.rzhd.poi.core.vm.BaseViewModel

class AuthViewModel : BaseViewModel() {

    fun onLoginClick() = postViewEvents(ViewEventStartAuth)
}