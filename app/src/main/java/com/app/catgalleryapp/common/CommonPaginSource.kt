package com.app.catgalleryapp.common

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CommonPagingSource<T : Any>(
    private val block: suspend (pageCount: Int, pageSize: Int, placeHolderEnabled: Boolean) -> List<T>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 0
        return try {
            val response = block(page, params.loadSize, params.placeholdersEnabled)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else (page - 1),
                nextKey = if (response.isEmpty()) null else (page + 1)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(Throwable("Uh-oh Something went wrong!"))
        }

    }
}