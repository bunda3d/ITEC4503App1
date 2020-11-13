package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
    (ProductData::class),
    (CartModel::class)
               ],
    version = 1
)
@Dao
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}