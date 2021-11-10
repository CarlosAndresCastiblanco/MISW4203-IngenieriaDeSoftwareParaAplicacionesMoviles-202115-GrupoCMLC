package com.uniandes.vinilos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumListScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.CollectorListScreenViewModel

@Composable
fun CollectorListScreen(
    navController: NavController,
    viewModel: CollectorListScreenViewModel = hiltViewModel()
) {
    val collectorsList by viewModel.getCollectors().observeAsState(initial = emptyList())
    CollectorListScreen(navController, collectorsList)

}

@Composable
fun CollectorListScreen(
    navController: NavController,
    collectors: List<Collector>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Coleccionistas") },
            )
        }
    )
    {
        LazyColumn (
            modifier = Modifier
                .padding(0.dp,0.dp, 0.dp, 60.dp)
                ) {
            items(collectors) { collector ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${Destinations.COLLECTOR_DETAILS_SCREEN}/${collector.id}")
                        },
                ) {

                    Row {
                        Column(Modifier.width(230.dp).padding(8.dp).align(Alignment.CenterVertically)) {
                            Text(collector.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(collector.telephone)
                            Text(collector.email)
                        }
                        Column(Modifier.padding(8.dp).align(Alignment.CenterVertically)) {
                            Text("Artistas Favoritos", fontSize = 11.sp,  fontWeight = FontWeight.Bold)
                            val countPerformers = collector.favoritePerformers.size ?: 0;
                            val countAlbums = collector.collectorAlbums.size?: 0
                            Text((countPerformers).toString(), fontSize = 11.sp)
                            Text("Total Álbumes", fontSize = 10.sp,  fontWeight = FontWeight.Bold)
                            Text((countAlbums).toString(), fontSize = 10.sp)
                        }
                    }

                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CollectorListPreview() {
    AlbumsAppTheme {
        CollectorListScreen(
            navController = rememberNavController(),
            collectors = arrayListOf(
                Collector("1", "Collecionista 1", "123456", "coleccionista1@music.com",
                arrayListOf(CollectorPerformers("1", "Artista 1", "https://asd.com")),
                arrayListOf(CollectorAlbums("1", "341 1", "Active")))
            )
        )
    }
}