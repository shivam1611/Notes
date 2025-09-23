package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.data.dao.NoteDao
import com.example.notes.data.entities.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object{
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}