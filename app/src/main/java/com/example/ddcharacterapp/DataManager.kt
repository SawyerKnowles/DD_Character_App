package com.example.ddcharacterapp

import com.example.ddcharacterapp.data.*

class DataManager {

    /** Notes Data **/
    var notesData = NotesData(ArrayList())

    /** Inventory Data **/
    var inventoryData = InventoryData(ArrayList())

    /** Ability Data **/
    var abilitiesData = AbilitiesData(ArrayList())

    /** Spell Data **/
    var spellsData = SpellsData(
        "",
        "",
        "",
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        ArrayList(),
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )


    /** Traits Data **/
    var traitsData = TraitsData(
        BasicCharacterData("","","","","",""),
        ComplexCharacterData("","","","","")
    )

    /** Stats Data **/
    var statsData = StatsData(
        HealthData("","","","",""),
        "",
        "",
        "",
        "",
        "",
        DeathSaveData(false,false,false,false,false,false),
        StrengthData("","",false,false),
        DexterityData("","",false,false,false,false),
        ConstitutionData("","",false),
        IntelligenceData("","",false,false,false,false,false,false),
        WisdomData("","",false,false,false,false,false,false),
        CharismaData("","",false,false,false,false,false)
    )
}