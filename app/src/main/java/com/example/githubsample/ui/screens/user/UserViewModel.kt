package com.example.githubsample.ui.screens.user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubsample.data.dto.Item
import com.example.githubsample.data.remote.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _searchTextPrivate = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchTextPrivate

    private val _searchItemPrivate = MutableStateFlow<PagingData<Item>>(PagingData.empty())
    val searchItem: MutableStateFlow<PagingData<Item>> get() = _searchItemPrivate

    fun searchTextChanged(searchText: String) {
        this._searchTextPrivate.value = searchText
    }

    fun getUser(searchText: String) {
        viewModelScope.launch {
            userRepository.getUser(searchText).cachedIn(viewModelScope).collect {
                _searchItemPrivate.value = it
            }
        }
    }
}