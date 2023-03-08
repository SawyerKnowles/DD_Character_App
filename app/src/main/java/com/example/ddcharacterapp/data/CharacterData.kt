package com.example.ddcharacterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ddcharacterapp.CharacterCardData

@Entity(tableName = "character_table")
data class CharacterData (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    //val characterData : CharacterCardData
)