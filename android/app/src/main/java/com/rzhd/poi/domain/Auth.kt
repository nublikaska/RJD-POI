package com.rzhd.poi.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

val isLoggedIn: Boolean
    get() = FirebaseAuth.getInstance().currentUser != null

val currentUser: FirebaseUser
    get() = FirebaseAuth.getInstance().currentUser!!