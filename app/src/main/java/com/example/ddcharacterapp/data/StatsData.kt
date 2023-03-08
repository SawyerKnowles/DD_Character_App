package com.example.ddcharacterapp.data

import android.text.Editable

data class StatsData (
    var hp : HealthData,
    var ac : String,
    var speed : String,
    var initiative : String,
    var pp : String,
    var str : StrengthData,
    var dex : DexterityData,
    var con : ConstitutionData,
    var int : IntelligenceData,
    var wis : WisdomData,
    var cha : CharismaData
    )