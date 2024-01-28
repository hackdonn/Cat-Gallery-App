package com.app.catgalleryapp.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.app.catgalleryapp.home.data.CatInfo
import com.app.catgalleryapp.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repo: HomeRepository
) : ViewModel() {
    var cats: Flow<PagingData<CatInfo>> = repo.getCats()
}