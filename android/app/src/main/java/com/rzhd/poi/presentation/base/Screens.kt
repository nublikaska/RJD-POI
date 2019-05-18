package com.rzhd.poi.presentation.base

import androidx.fragment.app.Fragment
import com.rzhd.poi.presentation.auth.AuthFragment
import com.rzhd.poi.presentation.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AuthScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = AuthFragment()
}

class MainScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = MainFragment()
}