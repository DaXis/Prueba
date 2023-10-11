package com.prueba.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class AbilitiesObj(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    val tableId: Int = 0,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_hidden")
    val isHidden: Boolean
) : Parcelable
