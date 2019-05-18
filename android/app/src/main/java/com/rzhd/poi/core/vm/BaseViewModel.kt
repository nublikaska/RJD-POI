package com.rzhd.poi.core.vm

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.rzhd.poi.core.event.ViewEvent
import com.rzhd.poi.core.lifecycle.BufferLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver, LifecycleOwner, CoroutineScope {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob + exceptionHandler

    val events: LiveData<ViewEvent>
        get() = _events

    init {

        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    protected open val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->

        Log.e(this::class.java.simpleName, "Exception:", exception)
    }

    private val _events = BufferLiveData<ViewEvent>()

    private val supervisorJob = SupervisorJob()

    protected fun postViewEvents(vararg events: ViewEvent) = events.forEach(_events::setValue)

    final override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        supervisorJob.cancel()
    }
}