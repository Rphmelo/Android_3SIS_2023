package br.com.fiap.marvelapp.data

import br.com.fiap.marvelapp.domain.MarvelCharacterModel
import br.com.fiap.marvelapp.domain.MarvelComicModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface MarvelRepository {
    suspend fun listComics(timestamp: String, apiKey: String, hash: String, characterId: Int): Response<MarvelComicModel>
    suspend fun listCharacters(timestamp: String, apiKey: String, hash: String): Response<MarvelCharacterModel>
}

class MarvelRepositoryImpl(
    private val service: MarvelService
) : MarvelRepository {

    override suspend fun listCharacters(timestamp: String, apiKey: String, hash: String): Response<MarvelCharacterModel> {
        return service.listCharacters(
            timestamp,
            apiKey,
            hash
        )
    }

    override suspend fun listComics(timestamp: String, apiKey: String, hash: String, characterId: Int): Response<MarvelComicModel> =
        withContext(Dispatchers.IO) {
            service.listComics(
                characterId,
                timestamp,
                apiKey,
                hash
            )
        }

}