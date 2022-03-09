package com.hd.wallpaper.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hd.wallpaper.bean.Photo
import com.hd.wallpaper.network.ApiService

class PhotoPagingSource(private val apiService: ApiService) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getImageList(nextPageNumber)
            val nextKey = nextPageNumber + 1
            LoadResult.Page(response.photos, null, nextKey)
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = null
}