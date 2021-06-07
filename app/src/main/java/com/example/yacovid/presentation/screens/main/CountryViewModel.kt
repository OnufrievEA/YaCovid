package com.example.yacovid.presentation.screens.main

import android.util.Log
import androidx.lifecycle.*
import com.example.yacovid.domain.model.Country
import com.example.yacovid.domain.usecase.CountryInteractor
import io.reactivex.disposables.CompositeDisposable

class CountryViewModel(private val interactor: CountryInteractor) : ViewModel(), LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()
    private val countryMutableLiveData = MutableLiveData<List<Country>>()

    val countryLiveData: LiveData<List<Country>> = countryMutableLiveData

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        compositeDisposable.add(
            interactor.getCountries()
                .subscribe({ result -> countryMutableLiveData.postValue(result) },
                    { throwable -> throwable.printStackTrace() })
        )
    }

    override fun onCleared() {
        Log.d("AAA", "onCleared: ")
        compositeDisposable.clear()
        super.onCleared()
    }
}