package com.mayur.shaadiapp.database

import androidx.room.*

@Dao
interface NameDao {

    @Query("SELECT * FROM nameinfo ORDER BY id DESC")
    fun getAllNameInfo(): List<NameEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNameList(name: NameEntity)

    @Delete
    fun deleteNameList(name: NameEntity?)

    @Update
    fun updateNameList(name: NameEntity?)

    @Query("UPDATE nameinfo SET status = :status_str WHERE id = :id")
    fun updateStatus(status_str: String, id: Int)

}