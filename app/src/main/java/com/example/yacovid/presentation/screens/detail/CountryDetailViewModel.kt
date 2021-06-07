package com.example.yacovid.presentation.screens.detail

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yacovid.domain.model.Country

class CountryDetailViewModel(val country: Country) : ViewModel() {

    val urlLoad = "https://www.countryflags.io/${country.code}/flat/64.png"

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

    override fun onCleared() {
        super.onCleared()
        Log.d("AAA", "onCleared: ")
    }

    fun convert(date: String?): String {
        return if (date == null) {
            "Нет данных"
        } else {
            val sb = StringBuilder(date)
            sb.removeRange(16, sb.length).replaceRange(10, 11, " ").toString()
        }
    }
}