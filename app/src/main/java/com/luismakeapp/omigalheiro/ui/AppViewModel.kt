package com.luismakeapp.omigalheiro.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismakeapp.omigalheiro.data.UiState
import com.luismakeapp.omigalheiro.database.Account
import com.luismakeapp.omigalheiro.database.AccountDao
import com.luismakeapp.omigalheiro.database.AccountRoomDatabase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()



    var contactDao: AccountDao? = null



    fun contactDao(context: Context){
        viewModelScope.launch {
            contactDao = AccountRoomDatabase.getDatabase(context).accountDao()
        }
    }

    fun insertAccount(account: Account){
        viewModelScope.launch {
            contactDao?.insertAccount(account)
        }
    }

    fun calcTotal(){
        viewModelScope.launch {

            val allData = contactDao?.getAllData()
            var accountResult = 0.0

            allData?.map {item ->
                if(item.positive){
                    accountResult += item.value
                }else{
                    accountResult -= item.value
                }
            }

            _uiState.update {
                it.copy(total = accountResult)
            }

        }
    }

    fun getAllAccount(){
        viewModelScope.launch {
            val allData: List<Account>? = contactDao?.getAllData()
            _uiState.update {
                it.copy(accounts = allData!!)
            }
        }
    }




}