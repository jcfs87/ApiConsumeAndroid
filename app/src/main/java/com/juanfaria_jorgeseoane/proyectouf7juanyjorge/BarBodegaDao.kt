package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import androidx.room.*


@Dao
interface BarBodegaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bar: BarBodega)

    @Update
    fun update(bar: BarBodega)

    @Delete
    fun delete(bar: BarBodega)

    @Query("SELECT * FROM barbodega")
    fun loadAllBars(): List<BarBodega>
}