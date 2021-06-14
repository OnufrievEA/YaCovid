package com.example.yacovid.presentation.screens.detail

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.AndroidViewModel
import com.example.yacovid.R
import com.example.yacovid.domain.model.Country
import javax.inject.Inject

class CountryDetailViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var country: Country

    val urlLoad by lazy { "https://www.countryflags.io/${country.code}/flat/64.png" }

    fun getConfirmedColor(): Int {
        return if (country.confirmed > 10000) Color.RED
        else Color.BLACK
    }

    fun getRecoveredColor(): Int {
        return if (country.recovered > 0.8 * country.confirmed) Color.GREEN
        else Color.BLACK
    }

    fun getDeathsColor(): Int {
        return if (country.deaths > 1000) Color.RED
        else Color.BLACK
    }

    fun convert(date: String?): String {
        return if (date == null) {
            getApplication<Application>().applicationContext.getString(R.string.no_data)
        } else {
            val sb = StringBuilder(date)
            sb.removeRange(16, sb.length).replaceRange(10, 11, " ").toString()
        }
    }
}