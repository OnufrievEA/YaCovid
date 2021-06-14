package com.example.yacovid.domain.usecase

import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class CountryInteractor @Inject constructor(private val repository: CountryRepository) {

    fun getCountries(): Single<List<Country>> {
        return repository.getCountries()
    }
}