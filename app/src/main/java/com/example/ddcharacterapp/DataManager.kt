package com.example.ddcharacterapp

import android.net.Uri
import com.example.ddcharacterapp.data.*

class DataManager {

    /**
     * Notes Data
     */
    private var initNotesList = ArrayList<NoteData>()
    var notesData = NotesData(initNotesList)

    /**
     * Inventory Data
     */
    private var initInventoryList = ArrayList<InventoryItemData>()
    var inventoryData = InventoryData(initInventoryList)

    /**
     * Ability Data
     */
    private var initAbilityData = ArrayList<AbilityData>()
    var abilitiesData = AbilitiesData(initAbilityData)

    /**
     * Spell Data
     */
    private var initCastingAbility = ""
    private var initSaveDC = ""
    private var initAttackBonus = ""
    private var init_level_0_spell_dataList = ArrayList<SpellData>()
    private var init_level_1_spell_dataList = ArrayList<SpellData>()
    private var init_level_2_spell_dataList = ArrayList<SpellData>()
    private var init_level_3_spell_dataList = ArrayList<SpellData>()
    private var init_level_4_spell_dataList = ArrayList<SpellData>()
    private var init_level_5_spell_dataList = ArrayList<SpellData>()
    private var init_level_6_spell_dataList = ArrayList<SpellData>()
    private var init_level_7_spell_dataList = ArrayList<SpellData>()
    private var init_level_8_spell_dataList = ArrayList<SpellData>()
    private var init_level_9_spell_dataList = ArrayList<SpellData>()

    var spellsData = SpellsData(
        initCastingAbility,
        initSaveDC,
        initAttackBonus,
        init_level_0_spell_dataList,
        init_level_1_spell_dataList,
        init_level_2_spell_dataList,
        init_level_3_spell_dataList,
        init_level_4_spell_dataList,
        init_level_5_spell_dataList,
        init_level_6_spell_dataList,
        init_level_7_spell_dataList,
        init_level_8_spell_dataList,
        init_level_9_spell_dataList
    )

    /**
     * Traits Data
     */
    private var initName = ""
    private var initCharacterClass = ""
    private var initLevel = ""
    private var initRace = ""
    private var initAlignment = ""
    private var initPicture = ""

    private var initBasicData = BasicCharacterData(
        initName,
        initCharacterClass,
        initLevel,
        initRace,
        initAlignment,
        initPicture
    )

    private var initPersonality = ""
    private var initIdeals = ""
    private var initBonds = ""
    private var initFlaws = ""
    private var initCharacterBackground = ""

    private var initComplexData = ComplexCharacterData(
        initPersonality,
        initIdeals,
        initBonds,
        initFlaws,
        initCharacterBackground
    )

    var traitsData = TraitsData(initBasicData, initComplexData)

    /**
     * Stats Data
     */
    //HP
    private var initHealthData = HealthData(
        "",
        "",
        "",
        "",
        "",
    )

    //Strength
    private var initScoreSTR = ""
    private var initModSTR = ""
    private var initSavingThrowSTR = false
    private var initAthletics = false
    private var initStrengthData = StrengthData(
        initScoreSTR,
        initModSTR,
        initSavingThrowSTR,
        initAthletics
    )

    //Dexterity
    private var initScoreDEX = ""
    private  var initModDEX = ""
    private var initSavingThrowDEX = false
    private var initAcrobatics = false
    private var initSleightOfHand = false
    private var initStealth = false
    private var initDexterityData = DexterityData(
        initScoreDEX,
        initModDEX,
        initSavingThrowDEX,
        initAcrobatics,
        initSleightOfHand,
        initStealth
    )

    //Constitution
    private var initScoreCON = ""
    private var initModCON = ""
    private var initSavingThrowCON = false
    private var initConstitutionData = ConstitutionData(
        initScoreCON,
        initModCON,
        initSavingThrowCON
    )

    //Intelligence
    private var initScoreINT = ""
    private var initModINT = ""
    private var initSavingThrowINT = false
    private var initArcana = false
    private var initHistory = false
    private var initInvestigation = false
    private var initNature = false
    private var initReligion = false
    private var initIntelligenceData = IntelligenceData(
        initScoreINT,
        initModINT,
        initSavingThrowINT,
        initArcana,
        initHistory,
        initInvestigation,
        initNature,
        initReligion,
    )

    //Wisdom
    private var initScoreWIS = ""
    private var initModWIS = ""
    private var initSavingThrowWIS = false
    private var initAnimalHandling = false
    private var initInsight = false
    private var initMedicine = false
    private var initPerception = false
    private var initSurvival = false
    private var initWisdomData = WisdomData(
        initScoreWIS,
        initModWIS,
        initSavingThrowWIS,
        initAnimalHandling,
        initInsight,
        initMedicine,
        initPerception,
        initSurvival
    )

    //Charisma
    private var initCharismaData = CharismaData(
        "",
        "",
        false,
        false,
        false,
        false,
        false,

    )

    var statsData = StatsData(
        initHealthData,
        "",
        "",
        "",
        "",
        initStrengthData,
        initDexterityData,
        initConstitutionData,
        initIntelligenceData,
        initWisdomData,
        initCharismaData

    )
}