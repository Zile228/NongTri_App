package com.example.nngtr

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val imageResId: String, // Lưu ID ảnh từ res/drawable
    val createdAt: String
)
