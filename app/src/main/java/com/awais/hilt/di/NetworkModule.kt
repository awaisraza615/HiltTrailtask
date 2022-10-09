package com.awais.hilt.di

import com.awais.hilt.ui.main.api.ApiInterface
import com.awais.hilt.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @Singleton
    @Provides
    fun providesDataAPI(retrofitBuilder: Retrofit.Builder): ApiInterface {
        return retrofitBuilder.build().create(ApiInterface::class.java)
    }



}