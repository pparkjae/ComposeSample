package com.example.githubsample.ui.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.githubsample.data.dto.Item
import com.example.githubsample.ui.theme.cardColor
import com.example.githubsample.ui.theme.typography

@Composable
fun UserListComposable(items: LazyPagingItems<Item>) {
    LazyColumn {
        items(
            items = items,
            itemContent = {
                UserListItem(user = it!!)
            }
        )
    }
}

@Composable
fun UserListItem(user: Item) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = 2.dp,
        backgroundColor = cardColor,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = user.avatarUrl
                ),
                contentDescription = "Git User Icon",
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = user.login, style = typography.h6)
                Text(text = user.url, style = typography.caption)
            }
        }
    }
}