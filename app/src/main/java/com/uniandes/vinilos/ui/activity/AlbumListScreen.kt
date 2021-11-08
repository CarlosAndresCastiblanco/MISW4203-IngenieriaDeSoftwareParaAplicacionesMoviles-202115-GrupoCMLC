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
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Performer
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumListScreenViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: AlbumListScreenViewModel = hiltViewModel()
) {
    val albumsList by viewModel.getAlbums().observeAsState(initial = emptyList())
    ListScreen(navController, albumsList)

}

@Composable
fun ListScreen(
    navController: NavController,
    albums: List<Album>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Álbumes") },
            )
        }
    )
    {
        LazyColumn (
            modifier = Modifier
                .padding(0.dp,0.dp, 0.dp, 60.dp)
                ) {
            items(albums) { album ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${Destinations.DETAILS_SCREEN}/${album.id}")
                        },
                ) {

                    Row {
                        Column(Modifier.width(250.dp).padding(8.dp)) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4f / 3f),
                                painter = rememberImagePainter(
                                    data = album.cover,
                                    builder = {
                                        placeholder(R.drawable.placeholder)
                                        error(R.drawable.placeholder)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                        }

                        Column(Modifier.padding(8.dp).align(Alignment.CenterVertically)) {
                            Text(album.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(album.performers[0].name, maxLines = 3)
                        }
                    }

                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    AlbumsAppTheme {
        ListScreen(
            navController = rememberNavController(),
            albums = arrayListOf(
                Album(
                    "1", "Album 1", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description", arrayListOf(
                        Performer("1", "Artista 1")
                    )                ),
                Album(
                    "2", "Album 2", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description", arrayListOf(
                        Performer("2", "Artista 2")
                    )
                ),
                Album(
                    "3", "Album 3", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description", arrayListOf(
                        Performer("3", "Artista 3")
                    )
                )
            )
        )
    }
}