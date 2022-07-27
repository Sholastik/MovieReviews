package com.vyacheslavivanov.moviereviews.di

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
                    val newUrl = it.request().url()
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
                    MoshiConverterFactory.create()
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
