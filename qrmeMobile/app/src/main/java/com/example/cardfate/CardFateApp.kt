package com.example.cardfate

import android.app.Application
import com.example.cardfate.di.DaggerApplicationComponent

class CardFateApp: Application() {

    val component by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this)
    }
}