package com.ynov.newsapp.everything.di

import com.ynov.newsapp.everything.data.repository.EverythingRepositoryImpl
import com.ynov.newsapp.everything.data.service.EverythingService
import com.ynov.newsapp.everything.domain.repository.EverythingRepository
import com.ynov.newsapp.everything.domain.useCase.EverythingUseCase
import com.ynov.newsapp.everything.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideEverythingService() : EverythingService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EverythingService::class.java)
    }

    @Provides
    @Singleton
    fun provideEverythingRepository(service: EverythingService) : EverythingRepository {
        return EverythingRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideEverythingUseCase(repository: EverythingRepository): EverythingUseCase {
        return EverythingUseCase(repository)
    }
}