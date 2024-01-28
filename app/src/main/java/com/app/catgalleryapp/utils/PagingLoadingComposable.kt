package com.app.catgalleryapp.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

@Composable
fun PagingLoadingComposable(loadState: CombinedLoadStates?) {
    when {
        loadState?.append is LoadState.Loading -> {
            Loader()
        }

        loadState?.refresh is LoadState.NotLoading -> {
            Loader()
        }

        loadState?.append is LoadState.Error || loadState?.refresh is LoadState.Error -> {
            Text(
                text = "Uh-Oh Something went wrong!"
            )
        }
    }
}