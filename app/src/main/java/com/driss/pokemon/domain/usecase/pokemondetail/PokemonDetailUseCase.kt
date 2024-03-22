package com.driss.pokemon.domain.usecase.pokemondetail

import com.driss.pokemon.data.entity.toModel
import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository
import com.driss.pokemon.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend fun execute(name: String): Flow<Result<Pokemon?>> {
        return flow {
            emit(Result.loading())
            val response = repository.getPokemonDetail(name = name)
            if (response.isSuccessful)
                emit(Result.success(response.body()?.toModel()))
            else
                emit(Result.error(message = "Failed to retrieve pokemon $name"))
        }

    }
}