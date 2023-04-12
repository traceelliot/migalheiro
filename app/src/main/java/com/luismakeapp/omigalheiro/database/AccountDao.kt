package com.luismakeapp.omigalheiro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AccountDao {

    @Query("SELECT * FROM account")
    suspend fun getAllData(): List<Account>

    @Insert
    suspend fun insertAccount(account: Account)
}