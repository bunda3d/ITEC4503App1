package com.krisbunda.gamesmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDb() {
    @PrimaryKey(autoGenerate = true) val id: Int?,

    @ColumnInfo val title: String?,

    @ColumnInfo val price: Double = 0.0
}