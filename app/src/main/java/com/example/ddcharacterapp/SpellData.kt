package com.example.ddcharacterapp

import android.text.Editable

data class SpellData (
    var title : Editable,
    var body : Editable,
    var expand : Boolean = false,
    var prepared : Boolean = false,
    var titleSaved : Boolean = false,
    var bodySaved : Boolean = false
)