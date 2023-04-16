package com.example.cardfate.di

import android.app.Application
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.fragment.*
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(signInFragment: SignInFragment)
    fun inject(logInFragment: LogInFragment)
    fun inject(createCardFragment: CreateCardFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(cardFragment: CardFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}