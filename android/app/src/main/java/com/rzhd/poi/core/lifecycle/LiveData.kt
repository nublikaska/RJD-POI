package com.rzhd.poi.core.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.rzhd.poi.core.extension.applyIfNotNull
import com.rzhd.poi.core.extension.observe
import com.rzhd.poi.core.extension.observeNotNull
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private class MutableLiveDataDelegate<in R, T : Any>(

        private val liveData: MutableLiveData<T>

) : ReadOnlyProperty<R, MutableLiveData<T>> {

    override fun getValue(thisRef: R, property: KProperty<*>): MutableLiveData<T> = liveData
}

fun <R, T : Any> mutableLiveData(initialValue: T? = null): ReadOnlyProperty<R, MutableLiveData<T>> {

    val liveData = MutableLiveData<T>().applyIfNotNull(initialValue) { value = initialValue }
    return MutableLiveDataDelegate(liveData)
}

fun <R, T : Any> LifecycleOwner.mutableLiveData(initialValue: T? = null, onChangedAction: (T?) -> Unit)
        : ReadOnlyProperty<R, MutableLiveData<T>> {

    val liveData = MutableLiveData<T>().applyIfNotNull(initialValue) { value = initialValue }
    observe(liveData, onChangedAction)
    return MutableLiveDataDelegate(liveData)
}

private class NotNullLiveDataDelegate<in R, T : Any>(

        private val liveData: NotNullLiveData<T>

) : ReadOnlyProperty<R, NotNullLiveData<T>> {

    override fun getValue(thisRef: R, property: KProperty<*>): NotNullLiveData<T> = liveData
}

fun <R, T : Any> notNullLiveData(initialValue: T): ReadOnlyProperty<R, NotNullLiveData<T>> {

    val liveData = NotNullLiveData(initialValue)
    return NotNullLiveDataDelegate(liveData)
}

fun <R, T : Any> LifecycleOwner.notNullLiveData(initialValue: T, onChangedAction: (T) -> Unit)
        : ReadOnlyProperty<R, NotNullLiveData<T>> {

    val liveData = NotNullLiveData(initialValue)
    observeNotNull(liveData, onChangedAction)
    return NotNullLiveDataDelegate(liveData)
}

class NotNullLiveData<T : Any>(initialValue: T) : MutableLiveData<T>() {

    init {

        value = initialValue
    }

    override fun getValue(): T = super.getValue()!!
}