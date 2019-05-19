package com.rzhd.poi.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

inline val isLoggedIn: Boolean
    get() = FirebaseAuth.getInstance().currentUser != null

inline val currentUser: FirebaseUser
    get() = FirebaseAuth.getInstance().currentUser!!