package com.example.chatapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.chatapp.api.ApiService
import com.example.chatapp.api.token.SessionManager
import com.example.chatapp.api.token.TokenAuthenticator
import com.example.chatapp.api.token.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager = SessionManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(sessionManager: SessionManager): TokenInterceptor =
       TokenInterceptor(sessionManager)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(sessionManager: SessionManager): TokenAuthenticator =
        TokenAuthenticator(sessionManager)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl("https://plannerok.ru/")
            .addConverterFactory(GsonConverterFactory.create())


    @Singleton
    @Provides
    fun provideAPIService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): ApiService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
}