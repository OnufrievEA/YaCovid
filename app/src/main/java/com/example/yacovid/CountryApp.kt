package com.example.yacovid

import android.app.Application
import com.example.yacovid.di.components.ApplicationComponent
import com.example.yacovid.di.components.DaggerApplicationComponent

class CountryApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }
}