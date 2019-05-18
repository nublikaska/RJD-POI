package com.rzhd.poi.presentation.auth

import com.rzhd.poi.core.event.ViewEventStartAuth
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.presentation.MainScreen
import ru.terrakok.cicerone.Router

class AuthViewModel(private val router: Router) : BaseViewModel() {

    fun onLoginClick() = postViewEvents(ViewEventStartAuth)

    fun onAuthSuccess() = router.newRootScreen(MainScreen())
}