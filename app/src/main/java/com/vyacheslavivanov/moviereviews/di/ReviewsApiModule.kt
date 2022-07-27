package com.vyacheslavivanov.moviereviews.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object ReviewsApiModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class ReviewsApi
}
