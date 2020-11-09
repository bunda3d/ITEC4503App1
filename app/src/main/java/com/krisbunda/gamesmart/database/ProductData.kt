package com.krisbunda.gamesmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true) var id: Int?,

    @ColumnInfo(name = "prod_title") var title: String?,

    @ColumnInfo(name = "prod_price_dol") var price: Double? = 0.0
)