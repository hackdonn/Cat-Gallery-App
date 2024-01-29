package com.app.catgalleryapp.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.catgalleryapp.R
import com.app.catgalleryapp.common.orDefault
import com.app.catgalleryapp.home.data.CatInfo
import com.app.catgalleryapp.utils.PagingLoadingComposable
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreen(
    galleryItems: Flow<PagingData<CatInfo>>
) {
    val catInfo = galleryItems.collectAsLazyPagingItems()
    val itemCount = catInfo.itemCount
    val loadState = catInfo.loadState
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        if (itemCount == 0 && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
            Text(stringResource(id = R.string.no_data_found))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = rememberLazyListState(),
                content = {
                    items(count = itemCount) { index ->
                        val catsInfo = catInfo[index]
                        catsInfo?.let {
                            GalleryItem(item = it)
                        }
                    }
                    item {
                        PagingLoadingComposable(loadState = loadState)
                    }
                }
            )
        }
    }
}

@Composable
fun GalleryItem(
    item: CatInfo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(152.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = item.url,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.cat_image)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = stringResource(R.string.id, item.id.orDefault()))
        }
    }
}