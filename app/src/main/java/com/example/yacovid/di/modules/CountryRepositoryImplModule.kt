package com.example.yacovid.di.modules

import com.example.yacovid.data.repository.CountryRepositoryImpl
import com.example.yacovid.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CountryRepositoryImplModule {

    @Binds
    abstract fun bindCountryRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository

}