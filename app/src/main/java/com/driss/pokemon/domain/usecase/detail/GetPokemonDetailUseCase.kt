package com.driss.pokemon.domain.usecase.detail

import com.driss.pokemon.domain.model.Pokemon
import com.driss.pokemon.domain.repository.PokemonRepository
import com.driss.pokemon.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {

    operator fun invoke(name: String): Flow<Result<Pokemon>> {
        return repository.getPokemonDetail(name)
    }
}