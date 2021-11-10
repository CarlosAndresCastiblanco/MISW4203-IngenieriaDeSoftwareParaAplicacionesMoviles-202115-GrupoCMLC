package com.uniandes.vinilos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.android.material.tabs.TabItem
import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumDetailsScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.AlbumListScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.CollectorDetailsScreenViewModel


@Composable
fun CollectorDetailsScreen(
    id: String,
    navController: NavController,
    viewModel: CollectorDetailsScreenViewModel = hiltViewModel(),
    voiewModel2: AlbumListScreenViewModel = hiltViewModel()
) {
    val collector by viewModel.getCollectorById(id).observeAsState(initial = null)
    val albums by voiewModel2.getAlbums().observeAsState(initial = null)
    CollectorDetailsScreen(id, navController, collector, albums)
}

@Composable
fun CollectorDetailsScreen(
    id: String,
    navController: NavController,
    collector: Collector?,
    albums: List<Album>?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(id + " - " + collector?.name.toString(), maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Artistas Favoritos", fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 20.dp)
            ) {
                if (collector != null) {


                    items(collector.favoritePerformers) { perfomer ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                            //shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .width(150.dp),
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4f / 3f),
                                painter = rememberImagePainter(
                                    data = perfomer.image,
                                    builder = {
                                        placeholder(R.drawable.placeholder)
                                        error(R.drawable.placeholder)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = perfomer.name,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Álbumes", fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 60.dp)
            ) {
                if (collector != null) {


                    items(collector.collectorAlbums) { album ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                            //shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .width(150.dp),
                        ) {
                            if (albums != null) {
                                val albumData: Album? = albums.find { it.id == album.id }
                                if (albumData != null) {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(4f / 3f),
                                        painter = rememberImagePainter(
                                            data = albumData.cover,
                                            builder = {
                                                placeholder(R.drawable.placeholder)
                                                error(R.drawable.placeholder)
                                            }
                                        ),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )


                                    Text(
                                        text = albumData.name,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "$"+album.price,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CollectorDetailsPreview() {
    AlbumsAppTheme {
        CollectorDetailsScreen(
            id = "1",
            navController = rememberNavController(),
            collector = Collector(
                "1", "Collecionista", "234567", "coleccionista@musica.com",
                arrayListOf(
                    CollectorPerformers("1", "Artista 1", "https://image.com"),
                    CollectorPerformers("2", "Artista 2", "https://image.com"),
                    CollectorPerformers("3", "Artista 3", "https://image.com"),
                    CollectorPerformers("4", "Artista 4", "https://image.com")
                ),
                arrayListOf(
                    CollectorAlbums("1", "123", "active"),
                    CollectorAlbums("2", "123", "active"),
                    CollectorAlbums("3", "123", "active"),
                    CollectorAlbums("4", "123", "active")

                )
            ),
            albums = arrayListOf(
                Album(
                    "1",
                    "Album 1",
                    "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z",
                    "Salsa",
                    "EMI",
                    "Album description",
                    arrayListOf(
                        Performer("1", "Artista 1")
                    )
                ),
                Album(
                    "2",
                    "Album 2",
                    "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z",
                    "Salsa",
                    "EMI",
                    "Album description",
                    arrayListOf(
                        Performer("2", "Artista 2")
                    )
                ),
                Album(
                    "3",
                    "Album 3",
                    "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z",
                    "Salsa",
                    "EMI",
                    "Album description",
                    arrayListOf(
                        Performer("2", "Artista 2")
                    )
                )
            )
        )
    }
}
