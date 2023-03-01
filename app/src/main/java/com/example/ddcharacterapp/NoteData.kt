package com.example.ddcharacterapp

import android.text.Editable

data class NoteData (
    var title : Editable,
    var body : Editable,
    var expand : Boolean = false,
    var saved : Boolean = false
)