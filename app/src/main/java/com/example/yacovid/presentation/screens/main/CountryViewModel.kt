package com.example.yacovid.presentation.screens.main

import androidx.lifecycle.*
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.usecase.CountryInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CountryViewModel : ViewModel() {

    @Inject
    lateinit var interactor: CountryInteractor

    private val compositeDisposable = CompositeDisposable()
    private val countryMutableLiveData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>().also {
            loadCountries()
        }
    }

    fun getCountryLiveData(): LiveData<List<Country>> {
        return countryMutableLiveData
    }

    private fun loadCountries() {
        compositeDisposable.add(
            interactor.getCountries()
                .subscribe({ result -> countryMutableLiveData.postValue(result) },
                    { throwable -> throwable.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}