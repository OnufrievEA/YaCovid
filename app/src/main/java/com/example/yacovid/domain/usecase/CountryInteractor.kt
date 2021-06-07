package com.example.yacovid.domain.usecase

import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository
import io.reactivex.Single

class CountryInteractor(private val repository: CountryRepository) {

    fun getCountries(): Single<List<Country>> {
        return repository.getCountries()
    }
}