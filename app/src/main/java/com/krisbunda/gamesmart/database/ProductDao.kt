package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM productdb")
    fun getAll(): List<ProductDb>

    @Insert
    fun insertAll(vararg products: ProductDb)

    @Delete
    fun delete(products: ProductDb)
}