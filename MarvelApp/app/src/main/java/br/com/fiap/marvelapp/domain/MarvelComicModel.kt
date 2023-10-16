package br.com.fiap.marvelapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MarvelComicModel(
    @SerializedName("data")
    val data: MarvelComicDataModel?
)

data class MarvelComicDataModel(
    @SerializedName("results")
    val results: List<MarvelComicDataResultModel>?
)

@Parcelize
data class MarvelComicDataResultModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail: MarvelComicDataResultThumbnailModel?,
): Parcelable {
    fun getThumbnailFullUrl(): String? = thumbnail?.run {
        "${path?.replace("http","https")}.$extension"
    }
}

@Parcelize
data class MarvelComicDataResultThumbnailModel(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
): Parcelable