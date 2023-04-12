package com.luismakeapp.omigalheiro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Account::class], version = 1)
abstract class AccountRoomDatabase: RoomDatabase() {

    abstract fun accountDao() : AccountDao

    companion object{
        private var INSTANCE: AccountRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): AccountRoomDatabase {
            //if the instance is not null, them return it
            //if it is, create a database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountRoomDatabase::class.java,
                    "account_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}