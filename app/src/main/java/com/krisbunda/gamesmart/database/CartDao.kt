package com.krisbunda.gamesmart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.krisbunda.gamesmart.model.Product

@Dao
interface CartDao {
    @Query("SELECT * FROM CartModel")
    fun getAll(): List<CartModel>

    @Insert
    fun insertAll(vararg item: CartModel)

    @Delete
    fun delete(item: CartModel)
}