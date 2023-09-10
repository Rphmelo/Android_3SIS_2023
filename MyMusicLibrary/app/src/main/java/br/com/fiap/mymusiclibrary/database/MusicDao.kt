package br.com.fiap.mymusiclibrary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MusicDao {

    @Query("SELECT * FROM $tableName")
    fun selectAll(): List<MusicModel>

    @Query("SELECT * FROM $tableName WHERE favorite == :isFavorite")
    fun selectBy(isFavorite: Boolean): List<MusicModel>

    @Insert
    fun insert(model: MusicModel)
}