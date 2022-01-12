package com.example.githubsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubsample.ui.navigation.NavRoute
import com.example.githubsample.ui.screens.user.UserScreen
import com.example.githubsample.ui.screens.user.UserViewModel
import com.example.githubsample.ui.theme.GithubSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubSampleTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = NavRoute.UserSearch.route) {
                    composable(NavRoute.UserSearch.route) {
                        UserScreen()
                    }
//                    composable("secondScreen") { UserScreen(viewModel) }
                }
            }
        }
    }

    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @Composable
    fun UserScreen() {
        val viewModel = hiltViewModel<UserViewModel>()

        UserScreen(
            viewModel = viewModel
        )
    }
}