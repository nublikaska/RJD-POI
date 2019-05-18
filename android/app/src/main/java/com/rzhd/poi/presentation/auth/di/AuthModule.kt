package com.rzhd.poi.presentation.auth.di

import com.rzhd.poi.presentation.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel { AuthViewModel(get()) }
}