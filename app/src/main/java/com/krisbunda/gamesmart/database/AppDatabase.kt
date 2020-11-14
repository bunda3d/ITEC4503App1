package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
    (ProductData::class)
               ], version = 1)
@Dao
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}