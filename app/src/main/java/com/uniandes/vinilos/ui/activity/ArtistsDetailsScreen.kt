package com.uniandes.vinilos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.uniandes.vinilos.model.*
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumListScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.ArtistsDetailsScreenViewModel
import com.uniandes.vinilos.ui.viewmodel.CollectorDetailsScreenViewModel
import java.text.SimpleDateFormat
import java.time.Instant


@Composable
fun ArtistsDetailsScreen(
    id: String,
    navController: NavController,
    viewModel: ArtistsDetailsScreenViewModel = hiltViewModel(),
) {
    val artist by viewModel.getArtistById(id).observeAsState(initial = null)
    ArtistsDetailsScreen(id, navController, artist)
}

@Composable
fun ArtistsDetailsScreen(
    id: String,
    navController: NavController,
    artist: Artist?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(id + " - " + artist?.name.toString(), maxLines = 1) },
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
        artist?.let {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Row {
                    Column (Modifier.width(250.dp)) {
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
                            contentScale = ContentScale.Fit,
                        )
                        Row (
                            Modifier
                                .width(Dp.Unspecified)
                                .padding(
                                    start = 30.dp,
                                    top = 8.dp,
                                    end = 8.dp,
                                    bottom = 8.dp
                                )

                        ) {
                            Text(
                                artist.description,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    Column (
                        Modifier
                            .width(Dp.Unspecified)
                            .padding(8.dp)) {
                        val context = LocalContext.current
                        Text("Nombre", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(artist.name, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                        val instant = Instant.parse(artist.birthDate)
                        val format_date =
                            SimpleDateFormat("yyyy-MM-dd").format(instant.toEpochMilli())
                        Text("Nacimiento:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(format_date, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtistsDetailsPreview() {
    AlbumsAppTheme {
        ArtistsDetailsScreen(
            id = "1",
            navController = rememberNavController(),
            artist = Artist(
                "1", "Artista 1", "http://image.com",
                "Artista del genero salsa", "1948-07-16T00:00:00.000Z"
            )
        )
    }
}
