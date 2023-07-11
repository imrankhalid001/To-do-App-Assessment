package com.astutesol.notesappmvvm.di

import com.astutesol.notesappmvvm.api.AuthInterceptor
import com.astutesol.notesappmvvm.api.NotesApi
import com.astutesol.notesappmvvm.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }





    @Singleton
    @Provides
    fun providesNotesApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): NotesApi {

        return retrofitBuilder
            .client(okHttpClient)
            .build().create(NotesApi::class.java)
    }

}