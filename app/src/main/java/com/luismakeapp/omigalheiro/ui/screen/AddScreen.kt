package com.luismakeapp.omigalheiro.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luismakeapp.omigalheiro.database.Account
import java.text.DecimalFormat
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun addScreen(
    goBackToMigalheiro: () -> (Unit),
    addAccount: (Account) -> (Unit),
    context: Context
){
    Scaffold(
        topBar = { topBarAddScreen( goBackToMigalheiro ) },
        content = { scaffoldContentAddScreen(addAccount, context) },
        backgroundColor = Color(0xFFFDF9F7)
    )
}


@Composable
fun topBarAddScreen(goBackToMigalheiro: () -> Unit){
    TopAppBar(
        modifier = Modifier.padding(5.dp),
        title = { Text(text = "Adicionar", color = Color(0xFF924642))},
        backgroundColor = Color(0xFFFBDEAC),
        navigationIcon = {
            IconButton(onClick = { goBackToMigalheiro() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color(0xFF924642))
            }
        }
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun scaffoldContentAddScreen(addAccount: (Account) -> (Unit), context: Context){

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        var value by remember {
           mutableStateOf("")
        }

        Text(text = "Montante:", fontSize = 26.sp)

        TextField(
            value = value,
            onValueChange = {value = it},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                          Text("Numeros com 2 casa decimais (Ex. 23.75)")
            },
            modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)

        )

        Button(onClick = {

            if(value.toDoubleOrNull() != null){
                var isDouble = value.split(".")
                if(isDouble.size == 2){
                    if (isDouble[1].length < 3){
                        addAccount(Account(value = value.toDouble(), date = LocalDate.now().toString(), positive = true))
                        keyboardController?.hide()
                        Toast.makeText(context, "Dinheiro adicionado", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Maximo 2 casas decimais", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Colocar pelo menos 1 casa decimal", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context, "Numero invalido", Toast.LENGTH_LONG).show()
            }


        }, modifier = Modifier.padding(top = 10.dp), colors = ButtonDefaults.buttonColors(Color(0xFFFBDEAC))) {
            Text(text = "Adicionar", textAlign = TextAlign.Center, color = Color(0xFF924642))
        }
    }

}