package com.rzhd.poi.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.rzhd.poi.R
import org.koin.dsl.module

val sharedPrefsModule = module {

    single<SharedPrefs> { SharedPrefsImpl(get()) }
}

private class SharedPrefsImpl(

        context: Context,
        sharedPrefsKey: String? = context.getString(R.string.app_key)

) : SharedPrefs {

    private val sharedPrefs: SharedPreferences by lazy {

        context.getSharedPreferences(
                sharedPrefsKey,
                Context.MODE_PRIVATE
        )
    }

    private inline fun transaction(transform: SharedPreferences.Editor.() -> Unit) {

        val editor = sharedPrefs.edit()
        editor.transform()
        val result = editor.commit()
        if (result.not()) throw IllegalStateException("Commit transaction failed.")
    }

    override fun put(key: String, value: Int) = transaction { putInt(key, value) }

    override fun put(key: String, value: Boolean) = transaction { putBoolean(key, value) }

    override fun put(key: String, value: String) = transaction { putString(key, value) }

    override fun remove(key: String) = transaction { remove(key) }

    override fun clear() = transaction { clear() }

    override fun getInt(key: String, defaultValue: Int?): Int {

        val value = defaultValue ?: SharedPrefs.DEFAULT_INT_VALUE

        return runCatching { sharedPrefs.getInt(key, value) }.getOrDefault(value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean?): Boolean {

        val value = defaultValue ?: SharedPrefs.DEFAULT_BOOLEAN_VALUE

        return runCatching { sharedPrefs.getBoolean(key, value) }.getOrDefault(value)
    }

    override fun getString(key: String, defaultValue: String?): String? {

        return runCatching { sharedPrefs.getString(key, defaultValue) }.getOrDefault(defaultValue)
    }
}