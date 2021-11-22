package com.uniandes.vinilos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.uniandes.vinilos.model.Album
import com.uniandes.vinilos.model.Performer
import com.uniandes.vinilos.ui.theme.AlbumsAppTheme
import com.uniandes.vinilos.ui.viewmodel.AlbumDetailsScreenViewModel
import java.text.SimpleDateFormat
import java.time.Instant

@Composable
fun DetailsScreen(
    id: String,
    navController: NavController,
    viewModel: AlbumDetailsScreenViewModel = hiltViewModel()
) {
    val new by viewModel.getAlbumById(id).observeAsState(initial = null)
    DetailsScreen(id, navController, new)
}

@Composable
fun DetailsScreen(
    id: String,
    navController: NavController,
    album: Album?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(id+" - "+ album?.name.toString(), maxLines = 1) },
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
        album?.let {
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
                                    data = album.cover,
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
                                    .padding(start = 30.dp,
                                        top = 8.dp,
                                        end = 8.dp,
                                        bottom = 8.dp)

                            ) {
                                Text(
                                    album.recordLabel,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                Text(
                                    " / ",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                val instant = Instant.parse(album.releaseDate)
                                val format_date =
                                    SimpleDateFormat("yyyy-MM-dd").format(instant.toEpochMilli())

                                Text(
                                    format_date,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Column (
                            Modifier
                                .width(Dp.Unspecified)
                                .padding(8.dp)) {
                            val context = LocalContext.current
                            Text("Titulo", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(album.name, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                            Text("Artista", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            if(album.performers.size > 0){
                                Text(album.performers[0].name, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                            }else{
                                Text("No disponible", fontSize = 18.sp, fontWeight = FontWeight.Normal)
                            }

                            Text("Genero", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(album.genre, fontSize = 18.sp, fontWeight = FontWeight.Normal)

                        }
                    }

            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    AlbumsAppTheme {
        DetailsScreen(
            id = "1",
            navController = rememberNavController(),
            album =  Album(
                "1", "Album 1", "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                "1948-07-16T00:00:00.000Z", "Salsa", "EMI", "Album description",  arrayListOf(
                    Performer("1", "Artista 1")
                )               ),
        )
    }
}
