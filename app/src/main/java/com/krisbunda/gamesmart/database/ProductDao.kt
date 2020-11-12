package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.krisbunda.gamesmart.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductData")
    fun getAll(): List<ProductData>

    @Query("SELECT * FROM ProductData WHERE prod_title LIKE :term")
    fun searchFor(term: String): List<ProductData>

    @Insert
    fun insertAll(vararg products: ProductData)

    @Delete
    fun delete(products: ProductData)
}