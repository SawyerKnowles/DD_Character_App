package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ddcharacterapp.data.*
import com.google.android.material.chip.Chip

class StatsFragment : Fragment() {

    var healthData = HealthData("","","","","")
    var deathSaveData = DeathSaveData(false,false,false,false,false,false)
    var STRData = StrengthData("","", false, false)
    var DEXData = DexterityData("","",false, false, false, false)
    var CONData = ConstitutionData("","",false)
    var INTData = IntelligenceData("","",false, false,false,false,false,false)
    var WISData = WisdomData("","",false,false,false,false,false,false)
    var CHAData = CharismaData("", "", false,false,false,false,false)
    var statsData = StatsData(healthData, "", "", "", "", "", deathSaveData, STRData, DEXData, CONData, INTData, WISData, CHAData)

    lateinit var maxHPEditText: EditText
    lateinit var tempHPEditText: EditText
    lateinit var hpEditText: EditText
    lateinit var hdEditText : EditText
    lateinit var maxHDEditText : EditText

    lateinit var acEditText : EditText
    lateinit var speedEditText : EditText
    lateinit var initEditText : EditText
    lateinit var ppEditText : EditText

    lateinit var deathSaveF1 : Chip
    lateinit var deathSaveF2 : Chip
    lateinit var deathSaveF3 : Chip
    lateinit var deathSaveS1 : Chip
    lateinit var deathSaveS2 : Chip
    lateinit var deathSaveS3 : Chip

    lateinit var proficienciesEditText: EditText

    lateinit var strScoreEditText : EditText
    lateinit var strModifierEditText : EditText
    lateinit var strSavingThrowCheckBox : CheckBox
    lateinit var strAthleticsCheckBox : CheckBox

    lateinit var dexScoreEditText : EditText
    lateinit var dexModifierEditText : EditText
    lateinit var dexSavingThrowCheckBox : CheckBox
    lateinit var dexAcrobaticsCheckBox : CheckBox
    lateinit var dexSleightOfHandCheckBox : CheckBox
    lateinit var dexStealthCheckBox : CheckBox

    lateinit var conScoreEditText : EditText
    lateinit var conModifierEditText : EditText
    lateinit var conSavingThrowCheckBox : CheckBox

    lateinit var intScoreEditText : EditText
    lateinit var intModifierEditText : EditText
    lateinit var intSavingThrowCheckBox : CheckBox
    lateinit var intArcanaCheckBox : CheckBox
    lateinit var intHistoryCheckBox : CheckBox
    lateinit var intInvestigationCheckBox : CheckBox
    lateinit var intNatureCheckBox : CheckBox
    lateinit var intReligionCheckBox : CheckBox

    lateinit var wisScoreEditText : EditText
    lateinit var wisModifierEditText : EditText
    lateinit var wisSavingThrowCheckBox : CheckBox
    lateinit var wisAnimalHandlingCheckBox : CheckBox
    lateinit var wisInsightCheckBox : CheckBox
    lateinit var wisMedicineCheckBox : CheckBox
    lateinit var wisPerceptionCheckBox : CheckBox
    lateinit var wisSurvivalCheckBox : CheckBox

    lateinit var chaScoreEditText : EditText
    lateinit var chaModifierEditText : EditText
    lateinit var chaSavingThrowCheckBox : CheckBox
    lateinit var chaDeceptionCheckBox : CheckBox
    lateinit var chaIntimidationCheckBox : CheckBox
    lateinit var chaPerformanceCheckBox : CheckBox
    lateinit var chaPersuasionCheckBox : CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        /*
        statsData.hp = mainAct.dataManager.statsData.hp
        statsData.ac = mainAct.dataManager.statsData.ac
        statsData.speed
        statsData.initiative
        statsData.pp
        statsData.str
        statsData.dex
        statsData.con
        statsData.int
        statsData.wis
        statsData.cha
         */
        Log.d("StatsFragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onDetach() {
        Log.d("StatsFragment", "StatsFragment destroyed.")
        super.onDetach()
    }

    override fun onPause() {
        Log.d("StatsFragment", "onPause Called.")
        writeToDataManager()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("StatsFragment", "StatsFragment created.")
        val mainAct =  (activity as MainActivity)
        mainAct.supportActionBar?.title = mainAct.dataManager.traitsData.basicData.name


        readFromDataManager()

        maxHPEditText = view.findViewById<EditText>(R.id.maxHP_editText)
        //maxHPEditText.isSaveEnabled = false
        //maxHPEditText.setText(statsData.hp.maxHP)
        setEditText(maxHPEditText, statsData.hp.maxHP)

        tempHPEditText = view.findViewById<EditText>(R.id.tempHP_editText)
        //tempHPEditText.isSaveEnabled = false
        //tempHPEditText.setText(statsData.hp.tempHP)
        setEditText(tempHPEditText, statsData.hp.tempHP)

        hpEditText = view.findViewById<EditText>(R.id.HP_editText)
        //hpEditText.isSaveEnabled = false
        //hpEditText.setText(statsData.hp.hp)
        setEditText(hpEditText, statsData.hp.hp)

        hdEditText = view.findViewById<EditText>(R.id.HD_editText)
        //hdEditText.isSaveEnabled = false
        //hdEditText.setText(statsData.hp.hitDice)
        setEditText(hdEditText, statsData.hp.hitDice)

        maxHDEditText = view.findViewById<EditText>(R.id.maxHD_editText)
        //maxHDEditText.isSaveEnabled = false
        //maxHDEditText.setText(statsData.hp.maxHitDice)
        setEditText(maxHDEditText, statsData.hp.maxHitDice)

        acEditText = view.findViewById<EditText>(R.id.AC_editText)
        //acEditText.isSaveEnabled = false
        //acEditText.setText(statsData.ac)
        setEditText(acEditText, statsData.ac)

        speedEditText = view.findViewById<EditText>(R.id.speed_editText)
        //speedEditText.isSaveEnabled = false
        //speedEditText.setText(statsData.speed)
        setEditText(speedEditText, statsData.speed)

        initEditText = view.findViewById<EditText>(R.id.INIT_editText)
        //initEditText.isSaveEnabled
        //initEditText.setText(statsData.initiative)
        setEditText(initEditText, statsData.initiative)

        ppEditText = view.findViewById<EditText>(R.id.PP_editText)
        //ppEditText.isSaveEnabled = false
        //ppEditText.setText(statsData.pp)
        setEditText(ppEditText, statsData.pp)

        deathSaveF1 = view.findViewById<Chip>(R.id.DS_chip_fail_1)
        //deathSaveF1.isSaveEnabled = false
        //deathSaveS1.isChecked = statsData.deathSaveData.fail_1
        setCheckBox(deathSaveF1, statsData.deathSaveData.fail_1)

        deathSaveF2 = view.findViewById<Chip>(R.id.DS_chip_fail_2)
        //deathSaveF2.isSaveEnabled = false
        //deathSaveS2.isChecked = statsData.deathSaveData.fail_2
        setCheckBox(deathSaveF2, statsData.deathSaveData.fail_2)

        deathSaveF3 = view.findViewById<Chip>(R.id.DS_chip_fail_3)
        //deathSaveF3.isSaveEnabled = false
        //deathSaveS3.isChecked = statsData.deathSaveData.fail_3
        setCheckBox(deathSaveF3, statsData.deathSaveData.fail_3)

        deathSaveS1 = view.findViewById<Chip>(R.id.DS_chip_pass_1)
        //deathSaveS1.isSaveEnabled = false
        //deathSaveS1.isChecked = statsData.deathSaveData.success_1
        setCheckBox(deathSaveS1, statsData.deathSaveData.success_1)

        deathSaveS2 = view.findViewById<Chip>(R.id.DS_chip_pass_2)
        //deathSaveS2.isSaveEnabled = false
        //deathSaveS2.isChecked = statsData.deathSaveData.success_2
        setCheckBox(deathSaveS2, statsData.deathSaveData.success_2)

        deathSaveS3 = view.findViewById<Chip>(R.id.DS_chip_pass_3)
        //deathSaveS3.isSaveEnabled = false
        //deathSaveS3.isChecked = statsData.deathSaveData.success_3
        setCheckBox(deathSaveS3, statsData.deathSaveData.success_3)

        proficienciesEditText = view.findViewById<EditText>(R.id.proficiencies_editText)
        //proficienciesEditText.isSaveEnabled = false
        //proficienciesEditText.setText(statsData.proficiencies)
        setEditText(proficienciesEditText, statsData.proficiencies)

        strScoreEditText = view.findViewById<EditText>(R.id.editText_STR_score)
        //strScoreEditText.isSaveEnabled = false
        //strScoreEditText.setText(statsData.str.score)
        setEditText(strScoreEditText, statsData.str.score)

        strModifierEditText = view.findViewById<EditText>(R.id.editText_STR_mod)
        //strModifierEditText.isSaveEnabled = false
        //strModifierEditText.setText(statsData.str.modifier)
        setEditText(strModifierEditText, statsData.str.modifier)

        strSavingThrowCheckBox = view.findViewById(R.id.STR_savingThrow)
        //strSavingThrowCheckBox.isSaveEnabled = false
        //strSavingThrowCheckBox.isChecked = statsData.str.savingThrow
        setCheckBox(strSavingThrowCheckBox, statsData.str.savingThrow)

        strAthleticsCheckBox = view.findViewById(R.id.STR_athletics)
        //strAthleticsCheckBox.isSaveEnabled = false
        //strAthleticsCheckBox.isChecked = statsData.str.athletics
        setCheckBox(strAthleticsCheckBox, statsData.str.athletics)

        dexScoreEditText = view.findViewById(R.id.editText_DEX_score)
        //dexScoreEditText.isSaveEnabled = false
        //dexScoreEditText.setText(statsData.dex.score)
        setEditText(dexScoreEditText, statsData.dex.score)

        dexModifierEditText = view.findViewById(R.id.editText_DEX_mod)
        //dexModifierEditText.isSaveEnabled = false
        //dexModifierEditText.setText(statsData.dex.modifier)
        setEditText(dexModifierEditText, statsData.dex.modifier)

        dexSavingThrowCheckBox = view.findViewById(R.id.DEX_savingThrow)
        //dexSavingThrowCheckBox.isSaveEnabled = false
        //dexSavingThrowCheckBox.isChecked = statsData.dex.savingThrow
        setCheckBox(dexSavingThrowCheckBox, statsData.dex.savingThrow)

        dexAcrobaticsCheckBox = view.findViewById(R.id.DEX_acrobatics)
        //dexAcrobaticsCheckBox.isSaveEnabled = false
        //dexAcrobaticsCheckBox.isChecked = statsData.dex.acrobatics
        setCheckBox(dexAcrobaticsCheckBox, statsData.dex.acrobatics)

        dexSleightOfHandCheckBox = view.findViewById(R.id.DEX_sleightofhand)
        //dexSleightOfHandCheckBox.isSaveEnabled = false
        //dexSleightOfHandCheckBox.isChecked = statsData.dex.acrobatics
        setCheckBox(dexSleightOfHandCheckBox, statsData.dex.sleightOfHand)

        dexStealthCheckBox = view.findViewById(R.id.DEX_stealth)
        //dexStealthCheckBox.isSaveEnabled = false
        //dexStealthCheckBox.isChecked = statsData.dex.stealth
        setCheckBox(dexStealthCheckBox, statsData.dex.stealth)

        conScoreEditText = view.findViewById(R.id.CON_editText_score)
        //conScoreEditText.isSaveEnabled = false
        //conScoreEditText.setText(statsData.con.score)
        setEditText(conScoreEditText, statsData.con.score)

        conModifierEditText = view.findViewById(R.id.CON_editText_mod)
        //conScoreEditText.isSaveEnabled = false
        //conScoreEditText.setText(statsData.con.modifier)
        setEditText(conModifierEditText, statsData.con.modifier)

        conSavingThrowCheckBox = view.findViewById(R.id.CON_savingThrow)
        //conSavingThrowCheckBox.isSaveEnabled = false
        //conSavingThrowCheckBox.isChecked = statsData.con.savingThrow
        setCheckBox(conSavingThrowCheckBox, statsData.con.savingThrow)

        intScoreEditText = view.findViewById(R.id.INT_editText_score)
        //intScoreEditText.isSaveEnabled = false
        //intScoreEditText.setText(statsData.int.score)
        setEditText(intScoreEditText, statsData.int.score)

        intModifierEditText = view.findViewById(R.id.INT_editText_mod)
        //intModifierEditText.isSaveEnabled = false
        //intModifierEditText.setText(statsData.int.modifier)
        setEditText(intModifierEditText, statsData.int.modifier)

        intSavingThrowCheckBox = view.findViewById(R.id.INT_savingThrow)
        //intSavingThrowCheckBox.isSaveEnabled = false
        //intSavingThrowCheckBox.isChecked = statsData.int.savingThrow
        setCheckBox(intSavingThrowCheckBox, statsData.int.savingThrow)

        intArcanaCheckBox = view.findViewById(R.id.INT_arcana)
        //intArcanaCheckBox.isSaveEnabled = false
        //intArcanaCheckBox.isChecked = statsData.int.arcana
        setCheckBox(intArcanaCheckBox, statsData.int.arcana)

        intHistoryCheckBox = view.findViewById(R.id.INT_history)
        //intHistoryCheckBox.isSaveEnabled = false
        //intHistoryCheckBox.isChecked = statsData.int.history
        setCheckBox(intHistoryCheckBox, statsData.int.history)

        intInvestigationCheckBox = view.findViewById(R.id.INT_investigation)
        //intInvestigationCheckBox.isSaveEnabled = false
        //intInvestigationCheckBox.isChecked = statsData.int.investigation
        setCheckBox(intInvestigationCheckBox, statsData.int.investigation)

        intNatureCheckBox = view.findViewById(R.id.INT_nature)
        //intNatureCheckBox.isSaveEnabled = false
        //intNatureCheckBox.isChecked = statsData.int.nature
        setCheckBox(intNatureCheckBox, statsData.int.nature)

        intReligionCheckBox = view.findViewById(R.id.INT_religion)
        //intReligionCheckBox.isSaveEnabled = false
        //intReligionCheckBox.isChecked = statsData.int.religion
        setCheckBox(intReligionCheckBox, statsData.int.religion)

        wisScoreEditText = view.findViewById(R.id.WIS_editText_score)
        setEditText(wisScoreEditText, statsData.wis.score)

        wisModifierEditText = view.findViewById(R.id.WIS_editText_mod)
        setEditText(wisModifierEditText, statsData.wis.modifier)

        wisSavingThrowCheckBox = view.findViewById(R.id.WIS_savingThrow)
        setCheckBox(wisSavingThrowCheckBox, statsData.wis.savingThrow)

        wisAnimalHandlingCheckBox = view.findViewById(R.id.WIS_animalhandling)
        setCheckBox(wisAnimalHandlingCheckBox, statsData.wis.animalHandling)

        wisInsightCheckBox = view.findViewById(R.id.WIS_insight)
        setCheckBox(wisInsightCheckBox, statsData.wis.insight)

        wisMedicineCheckBox = view.findViewById(R.id.WIS_medicine)
        setCheckBox(wisMedicineCheckBox, statsData.wis.medicine)

        wisPerceptionCheckBox = view.findViewById(R.id.WIS_perception)
        setCheckBox(wisPerceptionCheckBox, statsData.wis.perception)

        wisSurvivalCheckBox = view.findViewById(R.id.WIS_survival)
        setCheckBox(wisSurvivalCheckBox, statsData.wis.survival)

        chaScoreEditText = view.findViewById(R.id.CHA_editText_score)
        setEditText(chaScoreEditText, statsData.cha.score)

        chaModifierEditText = view.findViewById(R.id.CHA_editText_mod)
        setEditText(chaModifierEditText, statsData.cha.modifier)

        chaSavingThrowCheckBox = view.findViewById(R.id.CHA_savingThrow)
        setCheckBox(chaSavingThrowCheckBox, statsData.cha.savingThrow)

        chaDeceptionCheckBox = view.findViewById(R.id.CHA_deception)
        setCheckBox(chaDeceptionCheckBox, statsData.cha.deception)

        chaIntimidationCheckBox = view.findViewById(R.id.CHA_intimidation)
        setCheckBox(chaIntimidationCheckBox, statsData.cha.intimidation)

        chaPerformanceCheckBox = view.findViewById(R.id.CHA_performance)
        setCheckBox(chaPerformanceCheckBox, statsData.cha.performance)

        chaPersuasionCheckBox = view.findViewById(R.id.CHA_persuasion)
        setCheckBox(chaPersuasionCheckBox, statsData.cha.persuasion)
    }

    private fun setEditText(editText: EditText, string : String) {
        editText.isSaveEnabled = false
        editText.setText(string)
        return
    }

    private fun setCheckBox(checkBox: CheckBox, bool : Boolean) {
        checkBox.isSaveEnabled = false
        checkBox.isChecked = bool
        return
    }

    private fun readFromDataManager() {
        val mainAct =  (activity as MainActivity)
        statsData = mainAct.dataManager.statsData
    }

    private fun saveStatsDataState() {
        statsData.hp.maxHP = maxHPEditText.text.toString()
        statsData.hp.tempHP = tempHPEditText.text.toString()
        statsData.hp.hp = hpEditText.text.toString()
        statsData.hp.hitDice = hdEditText.text.toString()
        statsData.hp.maxHitDice = maxHDEditText.text.toString()

        statsData.ac = acEditText.text.toString()
        statsData.speed = speedEditText.text.toString()
        statsData.initiative = initEditText.text.toString()
        statsData.pp = ppEditText.text.toString()

        statsData.deathSaveData.fail_1 = deathSaveF1.isChecked
        statsData.deathSaveData.fail_2 = deathSaveF2.isChecked
        statsData.deathSaveData.fail_3 = deathSaveF3.isChecked
        statsData.deathSaveData.success_1 = deathSaveS1.isChecked
        statsData.deathSaveData.success_2 = deathSaveS2.isChecked
        statsData.deathSaveData.success_3 = deathSaveS3.isChecked

        statsData.proficiencies = proficienciesEditText.text.toString()

        statsData.str.score = strScoreEditText.text.toString()
        statsData.str.modifier = strModifierEditText.text.toString()
        statsData.str.savingThrow = strSavingThrowCheckBox.isChecked
        statsData.str.athletics = strAthleticsCheckBox.isChecked

        statsData.dex.score = dexScoreEditText.text.toString()
        statsData.dex.modifier = dexModifierEditText.text.toString()
        statsData.dex.savingThrow = dexSavingThrowCheckBox.isChecked
        statsData.dex.acrobatics = dexAcrobaticsCheckBox.isChecked
        statsData.dex.sleightOfHand = dexSleightOfHandCheckBox.isChecked
        statsData.dex.stealth = dexStealthCheckBox.isChecked

        statsData.con.score = conScoreEditText.text.toString()
        statsData.con.modifier = conModifierEditText.text.toString()
        statsData.con.savingThrow = conSavingThrowCheckBox.isChecked

        statsData.int.score = intScoreEditText.text.toString()
        statsData.int.modifier = intModifierEditText.text.toString()
        statsData.int.savingThrow = intSavingThrowCheckBox.isChecked
        statsData.int.arcana = intArcanaCheckBox.isChecked
        statsData.int.history = intHistoryCheckBox.isChecked
        statsData.int.investigation = intInvestigationCheckBox.isChecked
        statsData.int.nature = intNatureCheckBox.isChecked
        statsData.int.religion = intReligionCheckBox.isChecked

        statsData.wis.score = wisScoreEditText.text.toString()
        statsData.wis.modifier = wisModifierEditText.text.toString()
        statsData.wis.savingThrow = wisSavingThrowCheckBox.isChecked
        statsData.wis.animalHandling = wisAnimalHandlingCheckBox.isChecked
        statsData.wis.insight = wisInsightCheckBox.isChecked
        statsData.wis.medicine = wisMedicineCheckBox.isChecked
        statsData.wis.perception = wisPerceptionCheckBox.isChecked
        statsData.wis.survival = wisSurvivalCheckBox.isChecked

        statsData.cha.score = chaScoreEditText.text.toString()
        statsData.cha.modifier = chaModifierEditText.text.toString()
        statsData.cha.savingThrow = chaSavingThrowCheckBox.isChecked
        statsData.cha.deception = chaDeceptionCheckBox.isChecked
        statsData.cha.intimidation = chaIntimidationCheckBox.isChecked
        statsData.cha.performance = chaPerformanceCheckBox.isChecked
        statsData.cha.persuasion = chaPersuasionCheckBox.isChecked

    }

    private fun writeToDataManager() {
        val mainAct =  (activity as MainActivity)
        saveStatsDataState()
        mainAct.dataManager.statsData = statsData
    }



}