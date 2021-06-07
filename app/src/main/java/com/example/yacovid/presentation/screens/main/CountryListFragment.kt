package com.example.yacovid.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yacovid.R
import com.example.yacovid.data.network.Api
import com.example.yacovid.data.network.RetrofitInit
import com.example.yacovid.data.repository.CountryRepositoryImpl
import com.example.yacovid.domain.repository.CountryRepository
import com.example.yacovid.domain.usecase.CountryInteractor
import com.example.yacovid.presentation.screens.detail.CountryDetailFragment

class CountryListFragment : Fragment(), CountryAdapter.Listener {

    private lateinit var adapter: CountryAdapter
    lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_country_list, container, false)
        initRecycleView(root)
        return root
    }

    override fun onStart() {
        super.onStart()
        viewModel.countryLiveData.observe(this) { data -> adapter.setList(data) }
    }



    private fun initViewModel() {
        val api: Api = RetrofitInit.newApiInstance()
        val repository: CountryRepository = CountryRepositoryImpl(api)
        val interactor = CountryInteractor(repository)
        viewModel = ViewModelProvider(this, CountryViewModelFactory(interactor))
            .get(CountryViewModel::class.java)
        lifecycle.addObserver(viewModel)
    }

    private fun initRecycleView(root: View) {
        adapter = CountryAdapter()
        adapter.listener = this
        val rv = root.findViewById<RecyclerView>(R.id.rv_articles)
        rv.layoutManager = LinearLayoutManager(requireActivity())
        rv.adapter = adapter
    }

    override fun onClick(position: Int) {
        val transaction = parentFragmentManager.beginTransaction()
        val detailFragment = CountryDetailFragment()
        val args = Bundle()
        args.putParcelable(detailFragment.COUNTRY, adapter.countryList[position])
        detailFragment.arguments = args
        transaction.replace(R.id.container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}