package com.example.ddcharacterapp

import android.text.Editable

data class AbilityData (
    var title : Editable,
    var body : Editable,
    var expand : Boolean = false,
    var titleSaved : Boolean = false,
    var bodySaved : Boolean = false
)