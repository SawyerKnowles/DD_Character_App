package com.example.ddcharacterapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(application : Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<CharacterData>>
    private val repository : CharacterRepository

    init {
        val characterDao = CharacterDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        readAllData = repository.readAllData
    }

    fun addCharcter(character : CharacterData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }

}