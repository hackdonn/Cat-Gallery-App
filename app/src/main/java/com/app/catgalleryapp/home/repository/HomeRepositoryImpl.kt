package com.app.catgalleryapp.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.catgalleryapp.common.CommonPagingSource
import com.app.catgalleryapp.home.api.CatApi
import com.app.catgalleryapp.home.data.CatInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val catApi: CatApi
) : HomeRepository {

    override fun getCats(): Flow<PagingData<CatInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = true,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                CommonPagingSource { pageCount, pageSize, _ ->
                    catApi.getCats(pageCount, pageSize).body()?.toList().orEmpty()
                }
            }
        ).flow
    }
}