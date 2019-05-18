package com.rzhd.poi.presentation.auth

import com.rzhd.poi.core.event.ViewEventStartAuth
import com.rzhd.poi.core.lifecycle.notNullLiveData
import com.rzhd.poi.core.vm.BaseViewModel
import com.rzhd.poi.data.db.Repository
import com.rzhd.poi.presentation.base.MainScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class AuthViewModel(
    private val router: Router,
    private val repository: Repository
) : BaseViewModel() {

    val needShowLoading by notNullLiveData(false)

    fun onLoginClick() = postViewEvents(ViewEventStartAuth)

    fun onAuthSuccess() {

        launch {
            needShowLoading.value = true
            repository.getRoutes()
            needShowLoading.value = false
            router.newRootScreen(MainScreen())
        }
    }
}