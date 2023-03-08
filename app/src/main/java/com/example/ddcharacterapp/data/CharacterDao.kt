package com.example.ddcharacterapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacter(character : CharacterData)

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<CharacterData>>
}