package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM productdata")
    fun getAll(): List<ProductData>

    @Insert
    fun insertAll(vararg products: ProductData)

    @Delete
    fun delete(products: ProductData)
}