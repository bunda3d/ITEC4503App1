package com.krisbunda.gamesmart.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}