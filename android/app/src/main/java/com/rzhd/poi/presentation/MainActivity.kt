package com.rzhd.poi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rzhd.poi.R
import com.rzhd.poi.domain.isLoggedIn
import com.rzhd.poi.presentation.auth.AuthFragment
import com.rzhd.poi.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment = when {
            isLoggedIn -> MainFragment()
            else -> AuthFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}
