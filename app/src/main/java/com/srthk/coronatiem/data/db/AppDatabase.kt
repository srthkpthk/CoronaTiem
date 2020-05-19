package com.srthk.coronatiem.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srthk.coronatiem.data.db.entries.Statewise

@Database(
    entities = [Statewise::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNationalDataDao(): NationalDataDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "CoronaTiemDatabase.db"
            ).build()
    }
}