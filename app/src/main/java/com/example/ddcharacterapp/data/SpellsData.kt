package com.example.ddcharacterapp.data

import android.text.Editable

data class SpellsData (
    var castingAbility : String,
    var saveDC : String,
    var attackBonus : String,
    var level_0_spell_dataList : ArrayList<SpellData>,
    var level_1_spell_dataList : ArrayList<SpellData>,
    var level_2_spell_dataList : ArrayList<SpellData>,
    var level_3_spell_dataList : ArrayList<SpellData>,
    var level_4_spell_dataList : ArrayList<SpellData>,
    var level_5_spell_dataList : ArrayList<SpellData>,
    var level_6_spell_dataList : ArrayList<SpellData>,
    var level_7_spell_dataList : ArrayList<SpellData>,
    var level_8_spell_dataList : ArrayList<SpellData>,
    var level_9_spell_dataList : ArrayList<SpellData>,
    var level_1_spell_slots : String,
    var level_1_spell_slotMax : String,
    var level_2_spell_slots : String,
    var level_2_spell_slotMax : String,
    var level_3_spell_slots : String,
    var level_3_spell_slotMax : String,
    var level_4_spell_slots : String,
    var level_4_spell_slotMax : String,
    var level_5_spell_slots : String,
    var level_5_spell_slotMax : String,
    var level_6_spell_slots : String,
    var level_6_spell_slotMax : String,
    var level_7_spell_slots : String,
    var level_7_spell_slotMax : String,
    var level_8_spell_slots : String,
    var level_8_spell_slotMax : String,
    var level_9_spell_slots : String,
    var level_9_spell_slotMax : String
)