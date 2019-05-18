package com.rzhd.poi.core.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class BufferLiveData<T> : MutableLiveData<T>() where T : Any {

    private val buffer: Queue<T> = LinkedBlockingQueue<T>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        super.observe(owner, Observer {

            if (buffer.isNotEmpty()) drainBuffer(observer)
        })
    }

    override fun setValue(value: T?) {

        buffer.add(value)
        super.setValue(value)
    }

    private fun drainBuffer(observer: Observer<in T>) {

        var element = buffer.poll()
        while (element != null) {
            observer.onChanged(element)
            element = buffer.poll()
        }
    }
}