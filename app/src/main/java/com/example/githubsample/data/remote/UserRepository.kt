package com.example.githubsample.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubsample.data.dto.Item
import com.example.githubsample.data.service.UserService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
) {
    fun getUser(searchText: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 50, initialLoadSize = 50),
            pagingSourceFactory = {
                UserPagingSource(searchText, service)
            }
        ).flow
    }
}