package com.cgs.dailypulse.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cgs.dailypulse.screens.Screens
import com.cgs.dailypulse.screens.about.AboutScreen
import com.cgs.dailypulse.screens.articles.ArticlesScreen

data class TopAppBarConfig(
    val title: String,
    val navigationIcon: (@Composable (() -> Unit))? = null,
    val actions: @Composable (RowScope.() -> Unit) = {}
)

@Composable
fun AppNavHost(

) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val topAppBarConfig = getTopAppBarConfig(currentRoute, navController)

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = topAppBarConfig.title,
                navigationIcon = topAppBarConfig.navigationIcon,
                actions = topAppBarConfig.actions
            )
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen()
        }
        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen()
        }
    }
}

@Composable
fun getTopAppBarConfig(
    route: String?,
    navController: NavController
): TopAppBarConfig {
    return when (route) {
        Screens.ARTICLES.route -> TopAppBarConfig(
            title = "Articles",
            actions = {
                IconButton(
                    onClick = { navController.navigate(Screens.ABOUT_DEVICE.route) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "About Device Button"
                    )
                }
            }
        )

        Screens.ABOUT_DEVICE.route -> TopAppBarConfig(
            title = "About Device",
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Up Button"
                    )
                }
            }
        )

        else -> TopAppBarConfig(title = "")
    }
}
