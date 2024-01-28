package com.app.catgalleryapp.home.data

import com.app.catgalleryapp.common.or

data class GalleryResponseModel(
    val catList: List<CatInfo>?
)

data class CatInfo(
    val url: String?,
    val id: String?,
    val breeds: List<Breed>?
) {
    fun getBreed(): String = breeds?.get(0)?.name.or("Not Found")
}

data class Breed(
    val name: String?
)