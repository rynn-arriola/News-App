package com.example.rynnarriola.newsapp.util

import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.data.model.Language


object Constants {

    const val API_KEY = "18ddde37a9c64da79ce018d58328d411"
    const val COUNTRY = "us"
    const val DEBOUNCE_TIMEOUT = 300L
    const val MIN_SEARCH_CHAR = 3

    val COUNTRIES = listOf(
        Country("ar", "Argentina"),
        Country("au", "Australia"),
        Country("at", "Austria"),
        Country("be", "Belgium"),
        Country("br", "Brazil"),
        Country("bg", "Bulgaria"),
        Country("ca", "Canada"),
        Country("cn", "China"),
        Country("co", "Colombia"),
        Country("cu", "Cuba"),
        Country("cz", "Czech Republic"),
        Country("eg", "Egypt"),
        Country("fr", "France"),
        Country("de", "Germany"),
        Country("gr", "Greece"),
        Country("hk", "HongKong"),
        Country("hu", "Hungary"),
        Country("in", "India"),
        Country("id", "Indonesia"),
        Country("ie", "Ireland"),
        Country("il", "Israel"),
        Country("it", "Italy"),
        Country("jp", "Japan"),
        Country("lv", "Latvia"),
        Country("lt", "Lithuania"),
        Country("my", "Malaysia"),
        Country("mx", "Mexico"),
        Country("ma", "Morocco"),
        Country("nl", "Netherlands"),
        Country("nz", "New Zealand"),
        Country("ng", "Nigeria"),
        Country("no", "Norway"),
        Country("ph", "Philippines"),
        Country("pl", "Poland"),
        Country("pt", "Portugal"),
        Country("ro", "Romania"),
        Country("ru", "Russia"),
        Country("sa", "Saudi Arabia"),
        Country("rs", "Serbia"),
        Country("sg", "Singapore"),
        Country("sk", "Slovakia"),
        Country("si", "Slovenia"),
        Country("za", "South Africa"),
        Country("kr", "South Korea"),
        Country("se", "Sweden"),
        Country("ch", "Switzerland"),
        Country("tw", "Taiwan"),
        Country("th", "Thailand"),
        Country("tr", "Turkey"),
        Country("ae", "UAE"),
        Country("gb", "United Kingdom"),
        Country("us", "United States"),
        Country("ve", "Venezuela")
    )

    val LANGUAGES = listOf(
        Language("ar", "Argentina"),
        Language("de", "German"),
        Language("en", "English"),
        Language("es", "Spanish"),
        Language("fr", "French"),
        Language("he", "Hindi"),
        Language("it", "Italian"),
        Language("nl", "Dutch"),
        Language("no", "Norwegian"),
        Language("pt", "Portuguese"),
        Language("ru", "Russian"),
        Language("sv", "Swedish"),
        Language("zh", "China")
    )
}