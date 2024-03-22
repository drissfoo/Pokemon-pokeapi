package com.driss.pokemon.di

import com.driss.pokemon.data.repository.PokemonRepositoryImpl
import com.driss.pokemon.data.source.PokemonApi
import com.driss.pokemon.domain.repository.PokemonRepository
import com.driss.pokemon.domain.usecase.pokemonlist.PokemonListDataSourceFactory
import com.driss.pokemon.domain.usecase.pokemonlist.GetPokemonListUseCase
import com.driss.pokemon.presentation.pokemonlist.PokemonListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PokemonModule {
    @Provides
    fun providePokemonRepository(
        pokemonApi: PokemonApi
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }

    @Provides
    fun providePokemonListDataSourceFactory(
        pokemonRepository: PokemonRepository
    ): PokemonListDataSourceFactory {
        return PokemonListDataSourceFactory(pokemonRepository)
    }

    @Provides
    fun providePokemonListUseCase(
        pokemonRepository: PokemonListDataSourceFactory
    ): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonRepository)
    }

    @Provides
    fun providePokemonListViewModel(
        useCase: GetPokemonListUseCase
    ): PokemonListViewModel {
        return PokemonListViewModel(useCase)
    }
}