package br.com.fiap.marvelapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.marvelapp.BuildConfig
import br.com.fiap.marvelapp.domain.ComicDataResultModel
import br.com.fiap.marvelapp.shared.DependencyFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicViewModel : ViewModel() {

    val comicSuccessState = MutableLiveData<List<ComicDataResultModel>>()
    val comicErrorState = MutableLiveData<String>()


    suspend fun listComics(characterId: Int) {
        withContext(Dispatchers.Main) {
            val result = DependencyFactory.createRepository().listComics(
                DependencyFactory.timestamp,
                BuildConfig.MARVEL_API_KEY,
                DependencyFactory.hash,
                characterId
            )
            if(result.isSuccessful) {
                result.body()?.data?.results?.let {
                    comicSuccessState.value = it
                }
            } else {
                comicErrorState.value = "Infelizmente um erro na API da Marvel!"
            }
        }
    }
}