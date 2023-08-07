package br.com.fiap.countries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    var id: Int = 0,
    val name: String,
    val capital: String,
    val language: String,
    val currency: String,
    val location: String,
): Parcelable
