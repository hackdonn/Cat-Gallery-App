package com.app.catgalleryapp.home.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun provideGalleryRepo(galleryRepoImpl: HomeRepositoryImpl): HomeRepository
}