package com.krisbunda.gamesmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true) var id: Int?,

    @ColumnInfo(name = "prod_title") var title: String?,

    @ColumnInfo(name = "prod_photo_url") var photoUrl: String? =
        "https://via.placeholder.com/300/BB86FC/FFFFFF/?text=GameSmart",

    @ColumnInfo(name = "prod_desc") var descProd: String?,

    @ColumnInfo(name = "prod_price_dol") var price: Double? = 0.00,

    @ColumnInfo(name = "prod_price_pts") var points: Int? = 0
)