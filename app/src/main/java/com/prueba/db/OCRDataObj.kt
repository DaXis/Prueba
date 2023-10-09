package com.prueba.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class OCRDataObj(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "id_front_pic")
    var idFrontPic: String,

    @ColumnInfo(name = "id_back_pic")
    var idBackPic: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "second_last_name")
    var secondLastName: String,

    @ColumnInfo(name = "born_date")
    var bornDate: String,

    @ColumnInfo(name = "curp")
    var curp: String,

    @ColumnInfo(name = "cic")
    var cic: String,

    @ColumnInfo(name = "rfc")
    var rfc: String,

    @ColumnInfo(name = "elector_clave")
    var electorClave: String,

    @ColumnInfo(name = "emission_no")
    var emissionNo: String,

    @ColumnInfo(name = "ocr")
    var ocr: String,

    @ColumnInfo(name = "issue_date")
    var issueDate: String,

    @ColumnInfo(name = "registration_year")
    var registrationYear: String,

    @ColumnInfo(name = "validity")
    var validity: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "document")
    var document: String,

    @ColumnInfo(name = "passport_no")
    var passportNo: String,

    @ColumnInfo(name = "expiration_date")
    var expirationDate: String
) : Parcelable