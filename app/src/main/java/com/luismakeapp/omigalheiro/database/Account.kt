package com.luismakeapp.omigalheiro.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Double,
    val date: String,
    val positive: Boolean
)
