package com.example.githubsample.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubsample.data.dto.Item
import com.example.githubsample.data.service.UserService

class UserPagingSource constructor(
    private val searchText: String,
    private val service: UserService
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> =
        try {
            val pageNumber = params.key ?: DEFAULT_PAGE_NUMBER
            val user = service.getUser(searchText, pageNumber, params.loadSize)

            if (user.items.isNotEmpty()) {
                LoadResult.Page(
                    data = user.items,
                    prevKey = null,
                    nextKey = if (1000 < pageNumber * params.loadSize || user.items.size < params.loadSize) null else pageNumber + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1
    }
}