package com.luismakeapp.omigalheiro.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun totalScreen(
    goBackToMigalheiro: () -> (Unit),
    total: Double
){
    Scaffold(
        topBar = { topBarTotal(goBackToMigalheiro) },
        content = { scaffoldContentTotal(total) },
        backgroundColor = Color(0xFFFDF9F7)
    )
}

@Composable
fun topBarTotal(
    goBackToMigalheiro: () -> Unit
){
    TopAppBar(
        modifier = Modifier.padding(5.dp),
        title = {Text(text = "Total", color = Color(0xFF924642))},
        backgroundColor = Color(0xFFFBDEAC),
        navigationIcon = {
            IconButton(onClick = { goBackToMigalheiro() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color(0xFF924642))
            }
        }
    )
}

@Composable
fun scaffoldContentTotal(total: Double){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ){
        Row {
            Text(text = "Total:", fontSize = 36.sp)
            Text(text = "${String.format("%.2f", total)} €", fontSize = 36.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewTotalScreen(){
    totalScreen({ Log.d("navigation", "To screen Total")}, 1.0)
}