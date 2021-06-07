package com.example.yacovid.data.repository

import com.example.yacovid.data.network.Api
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.repository.CountryRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException

class CountryRepositoryImpl(private val api: Api) : CountryRepository {

    override fun getCountries(): Single<List<Country>> {
        return api.getCountries().subscribeOn(Schedulers.io())
    }
}