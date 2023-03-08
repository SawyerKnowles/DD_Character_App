package com.example.ddcharacterapp.data

import android.text.Editable

data class NoteData (
    var title : String,
    var body : String,
    var expand : Boolean = false,
    var titleSaved : Boolean = false,
    var bodySaved : Boolean = false
)