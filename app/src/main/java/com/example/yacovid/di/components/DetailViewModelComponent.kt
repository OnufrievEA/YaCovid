package com.example.yacovid.di.components

import com.example.yacovid.di.modules.DetailViewModelModule
import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.presentation.screens.detail.CountryDetailViewModel
import dagger.Component

@Component(modules = [DetailViewModelModule::class])
@FragmentScope
interface DetailViewModelComponent {
    fun inject(detailViewModel: CountryDetailViewModel)
}