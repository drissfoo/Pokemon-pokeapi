package com.driss.pokemon.di

import com.driss.pokemon.data.repository.PokemonRepositoryImpl
import com.driss.pokemon.data.source.PokemonApi
import com.driss.pokemon.data.source.paging.PokemonListDataSourceFactory
import com.driss.pokemon.domain.repository.PokemonRepository
import com.driss.pokemon.domain.usecase.list.GetPokemonListUseCase
import com.driss.pokemon.presentation.list.PokemonListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PokemonModule {
    @Provides
    fun providePokemonRepository(
        pokemonApi: PokemonApi,
        pagingFactory: PokemonListDataSourceFactory,
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi, pagingFactory)
    }

    @Provides
    fun providePokemonListDataSourceFactory(
        pokemonApi: PokemonApi
    ): PokemonListDataSourceFactory {
        return PokemonListDataSourceFactory(pokemonApi)
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