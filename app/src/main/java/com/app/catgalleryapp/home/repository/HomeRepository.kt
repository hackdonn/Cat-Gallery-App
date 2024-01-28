package com.app.catgalleryapp.home.repository

import androidx.paging.PagingData
import com.app.catgalleryapp.home.data.CatInfo
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCats(): Flow<PagingData<CatInfo>>
}