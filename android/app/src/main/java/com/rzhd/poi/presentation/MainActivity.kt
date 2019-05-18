package com.rzhd.poi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rzhd.poi.R
import com.rzhd.poi.presentation.auth.AuthFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, AuthFragment())
            .commit()
    }
}
