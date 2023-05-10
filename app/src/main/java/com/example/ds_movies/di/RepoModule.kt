package com.example.ds_movies.di

import com.example.ds_movies.data.api.MoviesApi
import com.example.ds_movies.data.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideMoviesRepo(moviesApi: MoviesApi):MoviesRepository {
        return MoviesRepository(moviesApi)
    }
}