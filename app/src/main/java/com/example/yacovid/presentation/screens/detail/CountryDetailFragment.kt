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

class CountryDetailFragment : Fragment() {

    lateinit var viewModel: CountryDetailViewModel
    val COUNTRY = "country"

    lateinit var flagImage: ImageView
    lateinit var countryName: TextView
    lateinit var confirmed: TextView
    lateinit var recovered: TextView
    lateinit var deaths: TextView
    lateinit var update: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            CountryDetailViewModelFactory(arguments?.getParcelable(COUNTRY)!!)
        )
            .get(CountryDetailViewModel::class.java)
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
        confirmed.setTextColor(viewModel.getConfirmedColor())
        recovered.setTextColor(viewModel.getRecoveredColor())
        deaths.setTextColor(viewModel.getDeathsColor())

        countryName.text = viewModel.country.country
        confirmed.text = viewModel.country.confirmed.toString()
        recovered.text = viewModel.country.recovered.toString()
        deaths.text = viewModel.country.deaths.toString()
        update.text = viewModel.convert(viewModel.country.lastUpdate)

        Glide.with(requireActivity())
            .load(viewModel.urlLoad)
            .error(R.drawable.no_flag)
            .into(flagImage)
    }


}