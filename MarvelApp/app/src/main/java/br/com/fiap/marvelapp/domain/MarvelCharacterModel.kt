package br.com.fiap.marvelapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MarvelCharacterModel(
   @SerializedName("data")
   val data: MarvelCharacterDataModel?
)

data class MarvelCharacterDataModel(
   @SerializedName("results")
   val results: List<MarvelCharacterDataResultModel>?
)

@Parcelize
data class MarvelCharacterDataResultModel(
    @SerializedName("id")
   val id: Int?,
    @SerializedName("name")
   val name: String?,
    @SerializedName("description")
   val description: String?,
    @SerializedName("thumbnail")
   val thumbnail: MarvelCharacterDataResultThumbnailModel?,
): Parcelable {
   fun getThumbnailFullUrl(): String? = thumbnail?.run {
      "${path?.replace("http","https")}.$extension"
   }
}

@Parcelize
data class MarvelCharacterDataResultThumbnailModel(
   @SerializedName("path")
   val path: String?,
   @SerializedName("extension")
   val extension: String?,
): Parcelable
