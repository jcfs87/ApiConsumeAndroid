package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class BarBodega(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "speciality") val speciality: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "web") val web: String?)
