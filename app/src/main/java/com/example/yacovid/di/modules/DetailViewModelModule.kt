package com.example.yacovid.di.modules

import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.domain.model.Country
import dagger.Module
import dagger.Provides

@Module
class DetailViewModelModule(private val country: Country) {
    @Provides
    @FragmentScope
    fun provideCountry(): Country {
        return country
    }
}