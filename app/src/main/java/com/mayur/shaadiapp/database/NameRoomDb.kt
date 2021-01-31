package com.mayur.shaadiapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameEntity::class], version = 1)
abstract class NameRoomDb : RoomDatabase() {

    abstract fun nameDao(): NameDao?

    companion object {
        private var INSTANCE: NameRoomDb? = null
        fun getAppDatabase(context: Context): NameRoomDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<NameRoomDb>(
                    context.applicationContext, NameRoomDb::class.java, "NameDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}