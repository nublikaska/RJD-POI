package com.rzhd.poi.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage

inline val isLoggedIn: Boolean
    get() = FirebaseAuth.getInstance().currentUser != null

inline val currentUser: FirebaseUser
    get() = FirebaseAuth.getInstance().currentUser!!

inline val storage: FirebaseStorage
    get() = FirebaseStorage.getInstance()

inline val String.asStorageName: String
    get() = toLowerCase().replace(' ', '_')