package com.uniandes.vinilos.ui.activity

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uniandes.vinilos.ui.viewmodel.CreateAlbumViewModel
import java.util.*


@Composable
fun CreateAlbumScreen(
    navController: NavController,
    viewModel: CreateAlbumViewModel = hiltViewModel())
{
    val name by viewModel.name.observeAsState()
    val cover by viewModel.cover.observeAsState()
    val genre by viewModel.genre.observeAsState()
    val recorder by viewModel.recorder.observeAsState()
    val description by viewModel.description.observeAsState()
    val releaseDate by viewModel.releaseDate.observeAsState()

    val valid by viewModel.valid.observeAsState()

    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,0,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            Log.d("TAG", "$dayOfMonth/$month/$year")
            viewModel.releaseDate.postValue("$dayOfMonth/$month/$year")
        }, year, month, day
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name ?: "",
            onValueChange = {viewModel.name.postValue(it)},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = cover ?: "",
            onValueChange = {viewModel.cover.postValue(it)},
            label = { Text("Cover") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = genre ?: "",
            onValueChange = {viewModel.genre.postValue(it)},
            label = { Text("Género") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = recorder?:"",
            onValueChange = {viewModel.recorder.postValue(it)},
            label = { Text("Disquera") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        OutlinedTextField(
            value = description ?:"",
            maxLines = 2,
            onValueChange = {viewModel.description.postValue(it)},
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Seleccionar Fecha de Lanzamiento")
        }

        Spacer(modifier = Modifier.size(8.dp))
        Button(modifier= Modifier.fillMaxWidth() ,enabled= valid?:false, onClick = {viewModel.enviar()}) {
            Text(text = "Guardar")
        }


    }
}