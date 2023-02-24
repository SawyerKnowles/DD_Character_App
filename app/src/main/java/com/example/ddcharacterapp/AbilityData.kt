package com.example.ddcharacterapp

import android.text.Editable

data class AbilityData (
    val title : String ="",
    val body : Editable,
    var expand : Boolean = false
)