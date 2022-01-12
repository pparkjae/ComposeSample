package com.example.githubsample.ui.screens.user

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubsample.data.dto.Item
import com.example.githubsample.ui.remember.rememberFlowWithLifecycle

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {

    val searchText by rememberFlowWithLifecycle(viewModel.searchText).collectAsState(initial = "")
    val userItems: LazyPagingItems<Item> = viewModel.searchItem.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            UserSearchTopBarComposable(
                searchText = searchText,
                onSearchTextChanged = {
                    viewModel.searchTextChanged(it)
                },
                onSearchClicked = {
                    viewModel.getUser(it)
                }
            )
        },
        content = {
            UserListComposable(userItems)
        })
}