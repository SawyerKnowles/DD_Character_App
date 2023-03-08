package com.example.ddcharacterapp.data

import androidx.lifecycle.LiveData

class CharacterRepository(private val characterDao : CharacterDao) {

    val readAllData : LiveData<List<CharacterData>> = characterDao.readAllData()

    suspend fun addCharacter(character : CharacterData) {
        characterDao.addCharacter(character)
    }

}