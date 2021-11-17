package com.uniandes.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.theme.BottomNavWithBadgesTheme
import com.uniandes.vinilos.ui.theme.navigation.BottomNavItem
import dagger.hilt.android.AndroidEntryPoint


object Destinations {
    const val LIST_SCREEN = "LIST_SCREEN"
    const val DETAILS_SCREEN = "DETAILS_SCREEN"

    const val COLLECTOR_LIST_SCREEN = "COLLECTOR_LIST_SCREEN"
    const val COLLECTOR_DETAILS_SCREEN = "COLLECTOR_DETAILS_SCREEN"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavWithBadgesTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottonNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                   name = "Álbumes",
                                   route = "home" ,
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Coleccionistas",
                                    route = "collectors" ,
                                    icon = Icons.Default.Person
                                ),
                                 BottomNavItem(
                                        name = "Premios",
                                        route = "awards" ,
                                        icon = Icons.Default.Star
                                 )
                            ) ,
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            })
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlbumsAppTheme {
        Greeting("Android")
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("collectors") {
            CollectorsScreen()
        }
        composable("awards") {
            AwardsScreen()
        }
    }
}

@Composable
fun BottonNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = Color.Black,
        elevation = 5.dp
    ) {
        items.forEach {
            item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if (selected) {
                            Text(item.name,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            )
        }

    }
}

@Composable fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AlbumsAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destinations.LIST_SCREEN,
                ) {
                    composable(Destinations.LIST_SCREEN) {
                        ListScreen(navController)
                    }
                    composable("${Destinations.DETAILS_SCREEN}/{id}") {
                        it.arguments?.getString("id")?.let { title ->
                            DetailsScreen(title, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable fun CollectorsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AlbumsAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destinations.COLLECTOR_LIST_SCREEN,
                ) {
                    composable(Destinations.COLLECTOR_LIST_SCREEN) {
                        CollectorListScreen(navController)
                    }
                    composable("${Destinations.COLLECTOR_DETAILS_SCREEN}/{id}") {
                        it.arguments?.getString("id")?.let { title ->
                            CollectorDetailsScreen(title, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable fun AwardsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Awards Screen")
    }
}