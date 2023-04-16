package com.example.cardfate.presentation.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.cardfate.R
import com.example.cardfate.presentation.fragment.CardFragment
import com.example.cardfate.presentation.fragment.SignInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkUserSigned()
        checkIntent()
    }

    private fun checkIntent() {
        if (intent?.data != null) {
            val data: Uri? = intent?.data
            val cardId: String = data.toString().substringAfter("?id=")
            navigateToFragment(CardFragment.newInstance(cardId))
        }
    }

    private fun checkUserSigned() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val login = pref.getString(LOGIN, null)
        if (login == null) {
            navigateToFragment(SignInFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    companion object {

        const val LOGIN = "login"
    }
}