package com.example.yacovid.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yacovid.domain.model.Country

class CountryDetailViewModelFactory(private val country: Country): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryDetailViewModel::class.java)) {
            return CountryDetailViewModel(country) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}