package com.prueba.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PokemonObj(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "sprite")
    val sprite: String,

    @ColumnInfo(name = "height")
    val height: String? = "",

    @ColumnInfo(name = "order")
    val order: String? = "",

    @ColumnInfo(name = "weight")
    val weight: String? = "",

    @ColumnInfo(name = "type_one")
    val typeOne: String? = "",

    @ColumnInfo(name = "type_two")
    val typeTwo: String? = ""

) : Parcelable