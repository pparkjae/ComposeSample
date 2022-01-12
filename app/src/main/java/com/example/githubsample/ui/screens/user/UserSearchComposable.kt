package com.example.githubsample.ui.screens.user

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun UserSearchTopBarComposable(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                onSearchTextChanged(it)
            },
            modifier = Modifier
                .weight(3f)
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                textAlign = TextAlign.Center
            ),
            label = {
                Text(
                    text = "User Name",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                onSearchClicked.invoke(searchText)
            },
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            Text(
                text = "Search",
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


