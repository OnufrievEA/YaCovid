package com.example.yacovid.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.yacovid.CountryApp
import com.example.yacovid.R
import com.example.yacovid.di.components.DaggerCountryListComponent
import com.example.yacovid.di.components.DaggerNetworkComponent
import com.example.yacovid.di.modules.CountryListModule
import com.example.yacovid.presentation.screens.detail.CountryDetailFragment
import javax.inject.Inject

class CountryListFragment : Fragment(), CountryAdapter.Listener {

    private lateinit var countryViewModel: CountryViewModel

    @Inject
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: CountryAdapter


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
        countryViewModel.getCountryLiveData()
            .observe(viewLifecycleOwner) { data -> adapter.setList(data) }
        return root
    }

    private fun initViewModel() {
        val component = DaggerNetworkComponent.builder()
            .applicationComponent(
                (activity?.application as CountryApp)
                    .getAppComponent()
            )
            .build()

        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        component.inject(countryViewModel)
    }

    private fun initRecycleView(root: View) {
        val countryListComponent = DaggerCountryListComponent.builder()
            .countryListModule(CountryListModule(this, root, requireContext()))
            .build()
        countryListComponent.inject(this)
    }

    override fun onClick(position: Int) {
        val transaction = parentFragmentManager.beginTransaction()
        val detailFragment = CountryDetailFragment()
        val args = Bundle()
        args.putParcelable(detailFragment.COUNTRY, adapter.countryList[position])
        detailFragment.arguments = args
        transaction.replace(R.id.container, detailFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}