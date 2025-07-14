package com.example.pagehub.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.pagehub.database.UserDao
import br.pagehub.room.LivroSalvoDao
import com.example.pagehub.data.model.User

@Database(entities = [User::class, LivroSalvoDao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun livroSalvoDao(): LivroSalvoDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pagehub_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
