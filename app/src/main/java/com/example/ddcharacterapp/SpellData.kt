package com.example.ddcharacterapp

import android.text.Editable

data class SpellData (
    val title : String ="",
    val body : Editable,
    var expand : Boolean = false
)