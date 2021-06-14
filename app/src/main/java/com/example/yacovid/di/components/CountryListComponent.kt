package com.example.yacovid.di.components

import androidx.recyclerview.widget.RecyclerView
import com.example.yacovid.di.modules.CountryListModule
import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.presentation.screens.main.CountryAdapter
import com.example.yacovid.presentation.screens.main.CountryListFragment
import dagger.Component

@Component(modules = [CountryListModule::class])
@FragmentScope
interface CountryListComponent {
    fun getCountryListAdapter(): CountryAdapter
    fun getRecyclerView(): RecyclerView
    fun inject(countryListFragment: CountryListFragment)
}