package com.uniandes.vinilos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.uniandes.vinilos.model.Artist
import com.uniandes.vinilos.model.Performer
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumListScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.ArtistsListScreenViewModel

@Composable
fun ArtistsListScreen(
    navController: NavController,
    viewModel: ArtistsListScreenViewModel = hiltViewModel()
) {
    val artistsList by viewModel.getArtists().observeAsState(initial = emptyList())
    ArtistsListScreen(navController, artistsList)

}

@Composable
fun ArtistsListScreen(
    navController: NavController,
    artists: List<Artist>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Artistas") },
            )
        }
    )
    {
        LazyColumn (
            modifier = Modifier
                .padding(0.dp,0.dp, 0.dp, 60.dp)
                ) {
            items(artists) { artist ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${Destinations.ARTIST_DETAIL_SCREEN}/${artist.id}")
                        },
                ) {

                    Row {
                        Column(
                            Modifier
                                .width(250.dp)
                                .padding(8.dp)) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(4f / 3f),
                                painter = rememberImagePainter(
                                    data = artist.image,
                                    builder = {
                                        placeholder(R.drawable.placeholder)
                                        error(R.drawable.placeholder)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                        }

                        Column(
                            Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)) {
                            Text(artist.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                        }
                    }

                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ArtistsListPreview() {
    AlbumsAppTheme {
        ArtistsListScreen(
            navController = rememberNavController(),
            artists = arrayListOf(
                Artist("1", "Artista1", "https://image.com", "Un Gran Artista", "1948-07-16T00:00:00.000Z"),
                Artist("2", "Artista2", "https://image.com", "Un Gran Artista", "1948-07-16T00:00:00.000Z"),

            )
        )
    }
}