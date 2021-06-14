package com.example.yacovid.di.components

import com.example.yacovid.data.network.Api
import com.example.yacovid.di.modules.NetworkModule
import com.example.yacovid.di.scopes.ApplicationScope
import dagger.Component

@Component(modules = [NetworkModule::class])
@ApplicationScope
interface ApplicationComponent {
    fun getApi(): Api
}