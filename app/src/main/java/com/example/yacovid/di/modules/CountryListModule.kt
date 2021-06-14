package com.example.yacovid.di.modules

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yacovid.R
import com.example.yacovid.di.scopes.FragmentScope
import com.example.yacovid.presentation.screens.main.CountryAdapter
import dagger.Module
import dagger.Provides

@Module
class CountryListModule(
    private val listener: CountryAdapter.Listener,
    private val view: View,
    private val context: Context
) {

    @Provides
    @FragmentScope
    fun provideCountryAdapter(): CountryAdapter {
        return CountryAdapter(listener)
    }

    @Provides
    @FragmentScope
    fun provideLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    @FragmentScope
    fun provideRecyclerView(
        layoutManager: RecyclerView.LayoutManager,
        adapter: CountryAdapter
    ): RecyclerView {
        val rv = view.findViewById<RecyclerView>(R.id.rv_articles)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        return rv
    }
}