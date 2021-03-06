package com.vini.movies.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vini.movies.data.local.MovieDatabase
import com.vini.movies.data.remote.TmdbApi
import com.vini.movies.data.repository.LocalDataSourceImpl
import com.vini.movies.data.repository.RemoteDataSourceImpl
import com.vini.movies.domain.repository.LocalDataSource
import com.vini.movies.domain.repository.RemoteDataSource
import com.vini.movies.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(contentType = contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdbApi(retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        tmdbApi: TmdbApi,
        movieDatabase: MovieDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(tmdbApi = tmdbApi, movieDatabase = movieDatabase)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        movieDatabase: MovieDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(movieDatabase = movieDatabase)
    }

}