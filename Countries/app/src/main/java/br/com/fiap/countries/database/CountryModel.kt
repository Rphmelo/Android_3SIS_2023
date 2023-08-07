package br.com.fiap.countries.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

const val COUNTRY_MODEL_TABLE_NAME = "countryTable"
@Entity(tableName = COUNTRY_MODEL_TABLE_NAME)
@Parcelize
data class CountryModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @NonNull @ColumnInfo val name: String,
    @NonNull @ColumnInfo val capital: String,
    @NonNull @ColumnInfo val language: String,
    @NonNull @ColumnInfo val currency: String,
    @NonNull @ColumnInfo val location: String,
): Parcelable
