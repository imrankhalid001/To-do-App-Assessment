package com.astutesol.notesappmvvm.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)