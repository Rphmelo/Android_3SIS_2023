package br.com.fiap.marvelapp.data

import br.com.fiap.marvelapp.domain.ComicModel
import br.com.fiap.marvelapp.domain.MarvelCharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface MarvelRepository {

    suspend fun listCharacters(
        timestamp: String,
        apiKey: String,
        hash: String
    ): Response<MarvelCharacterModel>

    suspend fun listComics(
        timestamp: String,
        apiKey: String,
        hash: String,
        characterId: Int
    ): Response<ComicModel>
}

class MarvelRepositoryImpl(
    private val service: MarvelService
): MarvelRepository {

    override suspend fun listCharacters(
        timestamp: String,
        apiKey: String,
        hash: String
    ): Response<MarvelCharacterModel> {
        return withContext(Dispatchers.IO) {
            service.listCharacters(
                timestamp,
                apiKey,
                hash
            )
        }
    }

    override suspend fun listComics(
        timestamp: String,
        apiKey: String,
        hash: String,
        characterId: Int
    ): Response<ComicModel> {
        return withContext(Dispatchers.IO) {
            service.listComics(
                timestamp,
                apiKey,
                hash,
                characterId
            )
        }
    }


}