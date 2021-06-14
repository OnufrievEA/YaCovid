package com.example.yacovid.di.components

import com.example.yacovid.di.modules.CountryRepositoryImplModule
import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.presentation.screens.main.CountryViewModel
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [CountryRepositoryImplModule::class]
)
@FragmentScope
interface NetworkComponent {
    fun inject(countryViewModel: CountryViewModel)
}