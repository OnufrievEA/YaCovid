package com.example.yacovid.domain.repository

import com.example.yacovid.domain.model.Country
import io.reactivex.Single

interface CountryRepository {
    fun getCountries(): Single<List<Country>>
}