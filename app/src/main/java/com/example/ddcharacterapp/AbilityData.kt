package com.example.ddcharacterapp

import android.text.Editable

data class AbilityData (
    var title : Editable,
    val body : Editable,
    var expand : Boolean = false
)