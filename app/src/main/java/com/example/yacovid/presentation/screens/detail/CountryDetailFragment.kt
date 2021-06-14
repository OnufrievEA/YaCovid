package com.example.yacovid.presentation.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.yacovid.R
import com.example.yacovid.di.components.DaggerDetailViewModelComponent
import com.example.yacovid.di.modules.DetailViewModelModule
import com.example.yacovid.domain.model.Country

class CountryDetailFragment : Fragment() {

    private lateinit var countryDetailViewModel: CountryDetailViewModel
    val COUNTRY = "country"

    lateinit var flagImage: ImageView
    lateinit var countryName: TextView
    lateinit var confirmed: TextView
    lateinit var recovered: TextView
    lateinit var deaths: TextView
    lateinit var update: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val country = requireArguments().getParcelable<Country>(COUNTRY)!!

        countryDetailViewModel = ViewModelProvider(this).get(CountryDetailViewModel::class.java)
        val component = DaggerDetailViewModelComponent.builder().detailViewModelModule(DetailViewModelModule(country)).build()
        component.inject(countryDetailViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(root: View) {
        flagImage = root.findViewById(R.id.flag_image)
        countryName = root.findViewById(R.id.country_name)
        confirmed = root.findViewById(R.id.confirmed_count)
        recovered = root.findViewById(R.id.recovered_count)
        deaths = root.findViewById(R.id.deaths_count)
        update = root.findViewById(R.id.update_date)

        showCountry()
    }

    private fun showCountry() {
        confirmed.setTextColor(countryDetailViewModel.getConfirmedColor())
        recovered.setTextColor(countryDetailViewModel.getRecoveredColor())
        deaths.setTextColor(countryDetailViewModel.getDeathsColor())

        countryName.text = countryDetailViewModel.country.country
        confirmed.text = countryDetailViewModel.country.confirmed.toString()
        recovered.text = countryDetailViewModel.country.recovered.toString()
        deaths.text = countryDetailViewModel.country.deaths.toString()
        update.text = countryDetailViewModel.convert(countryDetailViewModel.country.lastUpdate)

        Glide.with(requireActivity())
            .load(countryDetailViewModel.urlLoad)
            .error(R.drawable.no_flag)
            .into(flagImage)
    }
}