package com.luismakeapp.omigalheiro.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luismakeapp.omigalheiro.database.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun migalheiro(
    goToTotalScreen: () -> (Unit),
    goToAddScreen: () -> (Unit),
    goToWithDrawScreen: () -> (Unit),
    accounts: List<Account>
){

    val stateScaffold = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = stateScaffold,
        topBar = { topBar(stateScaffold, coroutineScope) },
        content = { scaffoldContent(accounts) },
        bottomBar = { bottomBar(goToAddScreen, goToWithDrawScreen) },
        drawerContent = { navDrawer(goToTotalScreen, coroutineScope, stateScaffold) },
        backgroundColor = Color(0xFFFDF9F7)
    )
}

@Composable
fun navDrawer(goToTotalScreen: () -> (Unit), coroutineScope: CoroutineScope, stateScaffold: ScaffoldState) {
    Column(
        modifier = Modifier
            .background(Color(0xFFFDF9F7))
            .fillMaxSize()
            .padding(20.dp)
        ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Menu", fontSize = 36.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
        }
        Row {
            IconButton(onClick = { /*TODO*/ }) {
                Row{
                    Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color(0xFF924642), modifier = Modifier.padding(6.dp))
                    Text(text = "Migalheiro", color = Color(0xFF924642), fontSize = 20.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
        Row {
            IconButton(onClick = {
                goToTotalScreen()
                coroutineScope.launch {
                    stateScaffold.drawerState.close()
                }
            }) {
                Row{
                    Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color(0xFF924642), modifier = Modifier.padding(6.dp))
                    Text(text = "Total", color = Color(0xFF924642), fontSize = 20.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
    }
}

@Composable
fun topBar(stateScaffold: ScaffoldState, coroutineScope: CoroutineScope){
    TopAppBar(
        modifier = Modifier.padding(5.dp),
        title = {Text(text = "O Migalheiro", color = Color(0xFF924642))},
        backgroundColor = Color(0xFFFBDEAC),
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    stateScaffold.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = null, tint = Color(0xFF924642))
            }
        }
    )
}

@Composable
fun scaffoldContent(accounts: List<Account>){
    LazyColumn(modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 65.dp)){
        items(accounts.size){item ->
            Card(
                elevation = 4.dp,
                backgroundColor = Color(0xFFFBDEAC),
                modifier = Modifier.padding(4.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    if (accounts[item].positive){
                        Row(){
                            Icon(Icons.Filled.Add, contentDescription = null, tint = Color(0xFF924642), modifier = Modifier.padding(top = 10.dp))
                            Text(text = "€", fontSize = 30.sp, modifier = Modifier.padding(start = 6.dp), color = Color(0xFF924642))
                            Text(text = accounts.get(item).value.toString(), fontSize = 30.sp, modifier = Modifier.padding(start = 6.dp), color = Color(0xFF924642))
                        }
                    }else{
                        Row(modifier = Modifier.padding(8.dp)){
                            Text(text = "-", fontSize = 40.sp, color = Color(0xFF924642))
                            Text(text = "€", fontSize = 30.sp, modifier = Modifier.padding(start = 10.dp, top = 6.dp), color = Color(0xFF924642))
                            Text(text = accounts.get(item).value.toString(), fontSize = 30.sp, modifier = Modifier.padding(start = 8.dp, top = 6.dp), color = Color(0xFF924642))
                        }
                    }
                    Row(modifier = Modifier.padding(8.dp)){
                        Text(text = "Data:", fontSize = 20.sp, color = Color(0xFF924642))
                        Text(text = accounts.get(item).date, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp), color = Color(0xFF924642))
                    }
                }
            }
        }
    }
}

@Composable
fun bottomBar(
    goToAddScreen: () -> (Unit),
    goToWithDrawScreen: () -> (Unit)
){
    BottomAppBar(backgroundColor = Color(0xFFFBDEAC), modifier = Modifier.padding(5.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = {
                goToAddScreen()
            }) {
                Icon(Icons.Filled.Add, contentDescription = null, tint = Color(0xFF924642), modifier = Modifier.padding(top = 12.dp))
            }
            IconButton(onClick = {
                goToWithDrawScreen()
            }) {
                //Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color(0xFF924642))
                Text(text = "-", fontSize = 40.sp, color = Color(0xFF924642), modifier = Modifier.padding(bottom = 20.dp))
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun previewMigalheiro(){
    migalheiro({ Log.d("navigation", "To screen Total")}, { Log.d("navigation", "To screen Total2")}, { Log.d("navigation", "To screen Total3")}, emptyList())
}