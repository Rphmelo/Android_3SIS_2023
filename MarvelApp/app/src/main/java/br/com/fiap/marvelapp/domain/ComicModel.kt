package br.com.fiap.marvelapp.domain

import com.google.gson.annotations.SerializedName

data class ComicModel(
    @SerializedName("data")
    val data: ComicDataModel
)

data class ComicDataModel(
    @SerializedName("results")
    val results: List<ComicDataResultModel>
)

data class ComicDataResultModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail: ComicDataResultThumbnailModel
) {
    fun getThumbnailFullUrl(): String {
        return "${thumbnail.path?.replace(
            "http",
            "https"
        )}.${thumbnail.extension}"
    }
}

data class ComicDataResultThumbnailModel(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
)
