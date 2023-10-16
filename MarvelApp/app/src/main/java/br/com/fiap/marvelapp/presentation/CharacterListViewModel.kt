package br.com.fiap.marvelapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.marvelapp.BuildConfig
import br.com.fiap.marvelapp.shared.DependencyFactory
import br.com.fiap.marvelapp.domain.MarvelCharacterDataResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterListViewModel : ViewModel() {

    val characterSuccessState = MutableLiveData<List<MarvelCharacterDataResultModel>>()
    val characterErrorState = MutableLiveData<String>()

    suspend fun listCharacters() {
        withContext(Dispatchers.IO) {
            val result = DependencyFactory.createRepository().listCharacters(
                DependencyFactory.timestamp,
                BuildConfig.MARVEL_API_KEY,
                DependencyFactory.hash
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