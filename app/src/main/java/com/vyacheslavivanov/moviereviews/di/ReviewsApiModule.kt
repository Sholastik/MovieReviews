package com.vyacheslavivanov.moviereviews.di

import com.vyacheslavivanov.moviereviews.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object ReviewsApiModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ReviewsApi

    @ReviewsApi
    @Provides
    @Reusable
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .header("api-key", BuildConfig.API_KEY)
                        .build()
                )
            }.build()
}
