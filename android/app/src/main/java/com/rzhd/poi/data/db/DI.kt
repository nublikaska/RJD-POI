package com.rzhd.poi.data.db

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dbModule = module {

    single { FirebaseFirestore.getInstance() }
    single<Repository> { RepositoryImpl(get()) }
}