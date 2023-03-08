package com.example.ddcharacterapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ddcharacterapp.data.NoteData

public class DataViewModel : ViewModel() {

    init {
        Log.d("ViewModel", "DataViewModel created!")
    }

    var dataList = MutableLiveData<ArrayList<NoteData>>()
    fun setDataList(list : ArrayList<NoteData>) {
        dataList.value = list
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "DataViewModel destroyed!")
    }

}