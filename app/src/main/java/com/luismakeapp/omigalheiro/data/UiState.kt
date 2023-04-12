package com.luismakeapp.omigalheiro.data

import android.content.Context
import com.luismakeapp.omigalheiro.database.Account


data class UiState(
    val accounts: List<Account> = emptyList(),
    val total: Double = 0.0
)
