package br.com.fiap.mymusiclibrary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val tableName = "music_table"
@Entity(tableName = tableName)
data class MusicModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val artist: String,
    @ColumnInfo
    val favorite: Boolean
)