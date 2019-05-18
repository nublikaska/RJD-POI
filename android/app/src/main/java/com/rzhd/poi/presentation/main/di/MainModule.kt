package com.rzhd.poi.presentation.main.di

import com.rzhd.poi.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel() }
}