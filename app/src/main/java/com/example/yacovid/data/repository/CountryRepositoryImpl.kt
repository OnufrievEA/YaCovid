package com.example.yacovid.data.repository

import com.example.yacovid.data.network.Api
import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class CountryRepositoryImpl @Inject constructor(private val api: Api) : CountryRepository {

    override fun getCountries(): Single<List<Country>> {
        return api.getCountries().subscribeOn(Schedulers.io())
    }
}