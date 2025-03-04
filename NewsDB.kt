package com.example.nngtr

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface NewsDAO {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsItem>

    @Insert
    fun insert(vararg news: NewsItem)

    @Delete
    fun delete(news: NewsItem)
}

@Database(entities = [NewsItem::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDAO(): NewsDAO

    companion object {
        @Volatile
        private var INSTANCE: NewsDB? = null

        fun getDatabase(context: Context): NewsDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDB::class.java,
                    "news-db"
                )
                    // Bạn nên tránh sử dụng allowMainThreadQueries() trong production,
                    // hãy sử dụng coroutine để chạy các thao tác database ngoài luồng main.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
