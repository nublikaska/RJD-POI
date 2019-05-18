package com.rzhd.poi.core.event

import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.rzhd.poi.domain.AUTH_REQ_CODE
import com.rzhd.poi.domain.authProviders

class ViewEventStartAuth : ViewEvent {

    override fun execute(fragment: Fragment) {

        val intent = AuthUI.getInstance().createSignInIntentBuilder()
            .enableAnonymousUsersAutoUpgrade()
            .setAvailableProviders(authProviders)
            .build()
        fragment.startActivityForResult(intent, AUTH_REQ_CODE)
    }
}