package com.mayur.shaadiapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// data class for DB
@Entity(tableName = "nameinfo")
data class NameEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = 0,
//    @ColumnInfo(name = "index") val index: Int,
    @ColumnInfo(name = "first") val firstName: String,
    @ColumnInfo(name = "last") val lastName: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "status") val status: String?
)