package com.uniandes.vinilos.ui.activity

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uniandes.vinilos.Destinations
import com.uniandes.vinilos.ui.viewmodel.CreateAlbumViewModel
import kotlinx.coroutines.flow.collectLatest
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
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val valid by viewModel.valid.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
                if(event){
                        Toast.makeText(context,"Álbum creado ",Toast.LENGTH_LONG).show()
                        navController.navigate(Destinations.LIST_SCREEN)
                }else{
                    Toast.makeText(context,"No sé pudo crear álbum",Toast.LENGTH_LONG).show()
                }
        }
    }

    //Dropdown Disqueras
    var expanded by remember { mutableStateOf(false)}
    val recordLabels = listOf("Sony Music","EMI", "Discos Fuentes", "Elektra", "Fania Records")
    var selectedRecordItem by remember { mutableStateOf("")}
    var textFiledSize by remember {mutableStateOf(Size.Zero)}

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }


    //Dropdown géneros
    var expanded1 by remember { mutableStateOf(false)}
    val  genres = listOf("Classical","Salsa","Rock","Folk")
    var selectGenreItem by remember { mutableStateOf("")}
    var textFiledSize1 by remember {mutableStateOf(Size.Zero) }
    val icon1 = if(expanded1){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }



    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    //calendar.set(year,month, day)

    val datePickerDialog = DatePickerDialog(
        context,0,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year,month, dayOfMonth)
            //viewModel.releaseDate.postValue("$dayOfMonth/$month/$year")
            viewModel.releaseDate.postValue(calendar.getTime().toString())
        }, year, month, day
    )
      Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Crear Álbum", fontSize = 30.sp,fontWeight = FontWeight.Bold)
            IconButton(
                onClick = { navController.navigate(Destinations.LIST_SCREEN) },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,"",
                )
            }
            OutlinedTextField(
                value = name ?: "",
                onValueChange = {viewModel.name.postValue(it)},
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().testTag("NAME"),

            )
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                value = cover ?: "",
                onValueChange = {viewModel.cover.postValue(it)},
                label = { Text("Cover") },
                modifier = Modifier.fillMaxWidth().testTag("COVER")
            )
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                value=selectGenreItem,
                onValueChange = {selectGenreItem=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("GENRE")
                    .onGloballyPositioned { coordinates1 ->
                        textFiledSize1 = coordinates1.size.toSize()
                    },
                enabled = false,
                label = {Text(text="Seleccione género")},
                trailingIcon =  {
                    Icon(
                        icon1,
                        "" ,
                        Modifier.clickable { expanded1= !expanded1 }
                    )
                }
            )
            DropdownMenu(
                expanded = expanded1,
                onDismissRequest = { expanded1 = false },
                modifier = Modifier.width(with(LocalDensity.current){textFiledSize1.width.toDp()})
            ) {
                genres.forEach{
                        label -> DropdownMenuItem(onClick = {
                    viewModel.genre.postValue(label)
                    selectGenreItem=label
                    expanded1=false
                }) {
                    Text(text=label)
                }
                }
            }
            /*OutlinedTextField(
                value = genre ?: "",
                onValueChange = {viewModel.genre.postValue(it)},
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth()
            )*/
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                value = selectedRecordItem,
                onValueChange = {selectedRecordItem= it},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("RECORD")
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = {Text(text= "Seleccione Disquera")},
                trailingIcon =  {
                    Icon(
                        icon,
                        "" ,
                        Modifier.clickable { expanded= !expanded }
                    )
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                recordLabels.forEach{
                        label -> DropdownMenuItem(onClick = {
                    viewModel.recorder.postValue(label)
                    selectedRecordItem=label
                    expanded=false
                }) {
                    Text(text=label)
                }
                }
            }
            /*OutlinedTextField(
                value = recorder?:"",
                onValueChange = {viewModel.recorder.postValue(it)},
                label = { Text("Disquera") },
                modifier = Modifier.fillMaxWidth()
            )*/
            Spacer(modifier = Modifier.size(4.dp))
            OutlinedTextField(
                value = description ?:"",
                maxLines = 2,
                onValueChange = {viewModel.description.postValue(it)},
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
                    .testTag("DESCRIPTION")
            )
            Spacer(modifier = Modifier.size(4.dp))
            Button(modifier = Modifier.testTag("DATE"),onClick = { datePickerDialog.show() }) {
                Text(text = "Seleccionar Fecha de Lanzamiento")
            }

            Spacer(modifier = Modifier.size(8.dp))
            Button(modifier= Modifier.fillMaxWidth() ,enabled= valid?:false,
                onClick = {
                    var res = viewModel.enviar()
                    Log.d("UI",res)
                    if(res == "OK"){
                        navController.navigate(Destinations.LIST_SCREEN)
                    }
                }) {
                Text(text = "Guardar")
            }

            Spacer(modifier = Modifier.size(8.dp))
            if(valid == false){
                Text("Complete los campos, No olvide seleccionar fecha", color = Color.Red)
            }







    }
}