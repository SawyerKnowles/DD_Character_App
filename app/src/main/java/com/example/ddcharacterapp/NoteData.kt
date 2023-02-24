package com.example.ddcharacterapp

import android.text.Editable

data class NoteData (
    val title : String ="",
    val body : Editable,
    var expand : Boolean = false
)