package com.vyacheslavivanov.moviereviews.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vyacheslavivanov.moviereviews.BuildConfig
import com.vyacheslavivanov.moviereviews.api.service.review.ReviewService
import com.vyacheslavivanov.moviereviews.api.source.review.ReviewSource
import com.vyacheslavivanov.moviereviews.api.source.review.ReviewSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReviewApiModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ReviewApi

    @ReviewApi
    @Binds
    @Reusable
    abstract fun bindReviewSource(
        reviewSourceImpl: ReviewSourceImpl
    ): ReviewSource

    companion object {
        @ReviewApi
        @Provides
        @Reusable
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor {
                    val newUrl = it.request().url
                        .newBuilder()
                        .addQueryParameter(
                            "api-key", BuildConfig.API_KEY
                        ).build()

                    it.proceed(
                        it.request()
                            .newBuilder()
                            .url(newUrl)
                            .build()
                    )
                }.apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY })
                    }
                }.build()

        @ReviewApi
        @Provides
        @Reusable
        fun provideRetrofit(
            @ReviewApi okHttpClient: OkHttpClient
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.REVIEWS_API_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
                ).build()

        @ReviewApi
        @Provides
        @Reusable
        fun provideReviewsService(
            @ReviewApi retrofit: Retrofit
        ): ReviewService =
            retrofit.create()
    }
}
