package com.rzhd.poi.domain

import com.firebase.ui.auth.AuthUI

val AUTH_REQ_CODE = 1009
val authProviders = listOf(AuthUI.IdpConfig.GoogleBuilder().build())