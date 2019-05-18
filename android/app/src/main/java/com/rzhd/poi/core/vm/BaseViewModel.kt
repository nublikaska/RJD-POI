package com.rzhd.poi.core.vm

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rzhd.poi.core.event.ViewEvent
import com.rzhd.poi.core.lifecycle.BufferLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob + exceptionHandler

    val events: LiveData<ViewEvent>
        get() = _events

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->

        Log.e(this::class.java.simpleName, "Exception:", exception)
    }

    private val _events = BufferLiveData<ViewEvent>()

    private val supervisorJob = SupervisorJob()

    protected fun postViewEvents(vararg events: ViewEvent) = events.forEach(_events::setValue)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancel()
    }
}