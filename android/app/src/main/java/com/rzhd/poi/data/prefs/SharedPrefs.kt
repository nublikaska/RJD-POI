package com.rzhd.poi.data.prefs

interface SharedPrefs {

    companion object {

        const val DEFAULT_INT_VALUE = -1
        const val DEFAULT_BOOLEAN_VALUE = false
        const val DEFAULT_STRING_VALUE = ""
    }

    fun put(key: String, value: Int)

    fun put(key: String, value: String)

    fun put(key: String, value: Boolean)

    fun getInt(key: String, defaultValue: Int? = DEFAULT_INT_VALUE): Int

    fun getString(key: String, defaultValue: String? = DEFAULT_STRING_VALUE): String?

    fun getBoolean(key: String, defaultValue: Boolean? = DEFAULT_BOOLEAN_VALUE): Boolean

    fun remove(key: String)

    fun clear()
}