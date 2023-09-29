package com.example.rynnarriola.newsapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.example.rynnarriola.newsapp.data.model.Countries
import com.example.rynnarriola.newsapp.databinding.ItemCountriesBinding


class CountriesAdapter(private val countryClicked: (Countries) -> Unit) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private val countriesList = mutableListOf<Countries>()

    inner class CountryViewHolder(private val binding: ItemCountriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCountry(country: Countries) {
            binding.countryButton.text = country.countries
            binding.countryButton.setOnClickListener { countryClicked(country) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            ItemCountriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countriesList[position]
        holder.bindCountry(country)
    }

    override fun getItemCount(): Int = countriesList.size

    fun submitList(countries: List<Countries>) {
        countriesList.clear()
        countriesList.addAll(countries)
        notifyDataSetChanged()
    }
}
