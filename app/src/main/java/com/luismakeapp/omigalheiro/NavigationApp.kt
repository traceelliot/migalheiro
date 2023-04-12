package com.luismakeapp.omigalheiro

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.luismakeapp.omigalheiro.ui.AppViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luismakeapp.omigalheiro.ui.screen.addScreen
import com.luismakeapp.omigalheiro.ui.screen.migalheiro
import com.luismakeapp.omigalheiro.ui.screen.totalScreen
import com.luismakeapp.omigalheiro.ui.screen.withDrawScreen

enum class Screen{
    MigalheiroScreen(),
    Total(),
    AddScreen(),
    WithdrawScreen()
}

@Composable
fun navigationApp(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    contextApp: Context
){

    val uiState by viewModel.uiState.collectAsState()

    viewModel.contactDao(contextApp)
    viewModel.getAllAccount()

    NavHost(navController = navController, startDestination = Screen.MigalheiroScreen.name){
        composable(route = Screen.MigalheiroScreen.name){
            migalheiro (
                {
                    navController.navigate(Screen.Total.name)
                    viewModel.calcTotal()
                },
                {navController.navigate(Screen.AddScreen.name)},
                {navController.navigate(Screen.WithdrawScreen.name)},
                uiState.accounts
            )
        }
        composable(route = Screen.Total.name){
            totalScreen (
                {
                    navController.popBackStack()
                    viewModel.getAllAccount()
                },
                uiState.total
            )
        }
        composable(route = Screen.AddScreen.name){
            addScreen (
                context = contextApp,
                goBackToMigalheiro = {
                    navController.popBackStack()
                    viewModel.getAllAccount()
                },
                addAccount = {
                    viewModel.insertAccount(it)
                }
            )
        }
        composable(route = Screen.WithdrawScreen.name){
            withDrawScreen (
                goBackToMigalheiro = {
                    navController.popBackStack()
                    viewModel.getAllAccount()
                },
                withDrawAccount = {
                    viewModel.insertAccount(it)
                },
                context = contextApp
            )
        }
    }

}

