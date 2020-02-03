package com.anangkur.beritaku.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anangkur.beritaku.util.Const

@Database(entities = arrayOf(AppEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getDao(): AppDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Const.DATABASE_NAME
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}