package br.com.fiap.marvelapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.marvelapp.BuildConfig
import br.com.fiap.marvelapp.shared.DependencyFactory
import br.com.fiap.marvelapp.domain.MarvelComicDataResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDetailViewModel : ViewModel() {

    val characterSuccessState = MutableLiveData<List<MarvelComicDataResultModel>>()
    val characterErrorState = MutableLiveData<String>()

    suspend fun listComics(characterId: Int) {
        withContext(Dispatchers.IO) {
            val result = DependencyFactory.createRepository().listComics(
                DependencyFactory.timestamp,
                BuildConfig.MARVEL_API_KEY,
                DependencyFactory.hash,
                characterId
            )
            withContext(Dispatchers.Main) {
                if(result.isSuccessful) {
                    result.body()?.data?.results?.let {
                        characterSuccessState.value = it
                    }
                } else {
                    characterErrorState.value = "Ocorreu um erro"
                }
            }
        }
    }
}