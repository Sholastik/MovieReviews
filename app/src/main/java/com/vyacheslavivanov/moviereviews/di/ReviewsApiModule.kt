package com.vyacheslavivanov.moviereviews.di

import com.vyacheslavivanov.moviereviews.BuildConfig
import com.vyacheslavivanov.moviereviews.api.service.reviews.ReviewsService
import com.vyacheslavivanov.moviereviews.api.source.reviews.ReviewsSource
import com.vyacheslavivanov.moviereviews.api.source.reviews.ReviewsSourceImpl
import com.vyacheslavivanov.moviereviews.api.source.reviews.paging.ReviewsPagingSource
import com.vyacheslavivanov.moviereviews.api.source.reviews.paging.ReviewsPagingSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
abstract class ReviewsApiModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ReviewsApi

    @ReviewsApi
    @Binds
    @Reusable
    abstract fun bindReviewsSource(
        reviewsSourceImpl: ReviewsSourceImpl
    ): ReviewsSource

    @ReviewsApi
    @Binds
    @Reusable
    abstract fun bindReviewsPagingSource(
        reviewsPagingSourceImpl: ReviewsPagingSourceImpl
    ): ReviewsPagingSource

    companion object {
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

        @ReviewsApi
        @Provides
        @Reusable
        fun provideRetrofit(
            @ReviewsApi okHttpClient: OkHttpClient
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.REVIEWS_API_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    MoshiConverterFactory.create()
                ).build()

        @ReviewsApi
        @Provides
        @Reusable
        fun provideReviewsService(
            @ReviewsApi retrofit: Retrofit
        ): ReviewsService =
            retrofit.create()
    }
}
