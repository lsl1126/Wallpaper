package com.hd.wallpaper.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hd.wallpaper.bean.Photo
import com.hd.wallpaper.network.RetrofitUtils
import com.hd.wallpaper.paging.PhotoPagingSource
import kotlinx.coroutines.flow.Flow

class PhotoViewModel : ViewModel() {
    fun getPhotoData(): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(20)) {
            PhotoPagingSource(RetrofitUtils.getApiUrl())
        }.flow.cachedIn(viewModelScope)
    }
}