package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.ddcharacterapp.data.*
import com.google.android.material.chip.Chip

class StatsFragment : Fragment() {

    private var healthData = HealthData("","","","","")
    private var deathSaveData = DeathSaveData(false,false,false,false,false,false)
    private var STRData = StrengthData("","", false, false)
    private var DEXData = DexterityData("","",false, false, false, false)
    private var CONData = ConstitutionData("","",false)
    private var INTData = IntelligenceData("","",false, false,false,false,false,false)
    private var WISData = WisdomData("","",false,false,false,false,false,false)
    private var CHAData = CharismaData("", "", false,false,false,false,false)
    private var statsData = StatsData(healthData, "", "", "", "", "", deathSaveData, STRData, DEXData, CONData, INTData, WISData, CHAData)

    private lateinit var maxHPEditText: EditText
    private lateinit var tempHPEditText: EditText
    private lateinit var hpEditText: EditText
    private lateinit var hdEditText : EditText
    private lateinit var maxHDEditText : EditText

    private lateinit var acEditText : EditText
    private lateinit var speedEditText : EditText
    private lateinit var initEditText : EditText
    private lateinit var ppEditText : EditText

    private lateinit var deathSaveF1 : Chip
    private lateinit var deathSaveF2 : Chip
    private lateinit var deathSaveF3 : Chip
    private lateinit var deathSaveS1 : Chip
    private lateinit var deathSaveS2 : Chip
    private lateinit var deathSaveS3 : Chip

    private lateinit var proficienciesEditText: EditText

    private lateinit var strScoreEditText : EditText
    private lateinit var strModifierEditText : EditText
    private lateinit var strSavingThrowCheckBox : CheckBox
    private lateinit var strAthleticsCheckBox : CheckBox

    private lateinit var dexScoreEditText : EditText
    private lateinit var dexModifierEditText : EditText
    private lateinit var dexSavingThrowCheckBox : CheckBox
    private lateinit var dexAcrobaticsCheckBox : CheckBox
    private lateinit var dexSleightOfHandCheckBox : CheckBox
    private lateinit var dexStealthCheckBox : CheckBox

    private lateinit var conScoreEditText : EditText
    private lateinit var conModifierEditText : EditText
    private lateinit var conSavingThrowCheckBox : CheckBox

    private lateinit var intScoreEditText : EditText
    private lateinit var intModifierEditText : EditText
    private lateinit var intSavingThrowCheckBox : CheckBox
    private lateinit var intArcanaCheckBox : CheckBox
    private lateinit var intHistoryCheckBox : CheckBox
    private lateinit var intInvestigationCheckBox : CheckBox
    private lateinit var intNatureCheckBox : CheckBox
    private lateinit var intReligionCheckBox : CheckBox

    private lateinit var wisScoreEditText : EditText
    private lateinit var wisModifierEditText : EditText
    private lateinit var wisSavingThrowCheckBox : CheckBox
    private lateinit var wisAnimalHandlingCheckBox : CheckBox
    private lateinit var wisInsightCheckBox : CheckBox
    private lateinit var wisMedicineCheckBox : CheckBox
    private lateinit var wisPerceptionCheckBox : CheckBox
    private lateinit var wisSurvivalCheckBox : CheckBox

    private lateinit var chaScoreEditText : EditText
    private lateinit var chaModifierEditText : EditText
    private lateinit var chaSavingThrowCheckBox : CheckBox
    private lateinit var chaDeceptionCheckBox : CheckBox
    private lateinit var chaIntimidationCheckBox : CheckBox
    private lateinit var chaPerformanceCheckBox : CheckBox
    private lateinit var chaPersuasionCheckBox : CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        setupHP(view)
        setupGeneral(view)
        setupDeathSaves(view)
        setupStrength(view)
        setupDexterity(view)
        setupConstitution(view)
        setupIntelligence(view)
        setupWisdom(view)
        setupCharisma(view)
        setupChangeHP(view)
    }

    private fun setupHP(view: View) {
        maxHPEditText = view.findViewById(R.id.maxHP_editText)
        setEditText(maxHPEditText, statsData.hp.maxHP)

        tempHPEditText = view.findViewById(R.id.tempHP_editText)
        setEditText(tempHPEditText, statsData.hp.tempHP)

        hpEditText = view.findViewById(R.id.HP_editText)
        setEditText(hpEditText, statsData.hp.hp)

        hdEditText = view.findViewById(R.id.HD_editText)
        setEditText(hdEditText, statsData.hp.hitDice)

        maxHDEditText = view.findViewById(R.id.maxHD_editText)
        setEditText(maxHDEditText, statsData.hp.maxHitDice)
    }

    private fun setupGeneral(view: View) {
        acEditText = view.findViewById(R.id.AC_editText)
        setEditText(acEditText, statsData.ac)

        speedEditText = view.findViewById(R.id.speed_editText)
        setEditText(speedEditText, statsData.speed)

        initEditText = view.findViewById(R.id.INIT_editText)
        setEditText(initEditText, statsData.initiative)

        ppEditText = view.findViewById(R.id.PP_editText)
        setEditText(ppEditText, statsData.pp)

        proficienciesEditText = view.findViewById(R.id.proficiencies_editText)
        setEditText(proficienciesEditText, statsData.proficiencies)
    }

    private fun setupDeathSaves(view: View) {
        deathSaveF1 = view.findViewById(R.id.DS_chip_fail_1)
        setCheckBox(deathSaveF1, statsData.deathSaveData.fail_1)

        deathSaveF2 = view.findViewById(R.id.DS_chip_fail_2)
        setCheckBox(deathSaveF2, statsData.deathSaveData.fail_2)

        deathSaveF3 = view.findViewById(R.id.DS_chip_fail_3)
        setCheckBox(deathSaveF3, statsData.deathSaveData.fail_3)

        deathSaveS1 = view.findViewById(R.id.DS_chip_pass_1)
        setCheckBox(deathSaveS1, statsData.deathSaveData.success_1)

        deathSaveS2 = view.findViewById(R.id.DS_chip_pass_2)
        setCheckBox(deathSaveS2, statsData.deathSaveData.success_2)

        deathSaveS3 = view.findViewById(R.id.DS_chip_pass_3)
        setCheckBox(deathSaveS3, statsData.deathSaveData.success_3)
    }

    private fun setupStrength(view: View) {
        strScoreEditText = view.findViewById(R.id.editText_STR_score)
        setEditText(strScoreEditText, statsData.str.score)

        strModifierEditText = view.findViewById(R.id.editText_STR_mod)
        setEditText(strModifierEditText, statsData.str.modifier)

        strSavingThrowCheckBox = view.findViewById(R.id.STR_savingThrow)
        setCheckBox(strSavingThrowCheckBox, statsData.str.savingThrow)

        strAthleticsCheckBox = view.findViewById(R.id.STR_athletics)
        setCheckBox(strAthleticsCheckBox, statsData.str.athletics)
    }

    private fun setupDexterity(view: View) {
        dexScoreEditText = view.findViewById(R.id.editText_DEX_score)
        setEditText(dexScoreEditText, statsData.dex.score)

        dexModifierEditText = view.findViewById(R.id.editText_DEX_mod)
        setEditText(dexModifierEditText, statsData.dex.modifier)

        dexSavingThrowCheckBox = view.findViewById(R.id.DEX_savingThrow)
        setCheckBox(dexSavingThrowCheckBox, statsData.dex.savingThrow)

        dexAcrobaticsCheckBox = view.findViewById(R.id.DEX_acrobatics)
        setCheckBox(dexAcrobaticsCheckBox, statsData.dex.acrobatics)

        dexSleightOfHandCheckBox = view.findViewById(R.id.DEX_sleightofhand)
        setCheckBox(dexSleightOfHandCheckBox, statsData.dex.sleightOfHand)

        dexStealthCheckBox = view.findViewById(R.id.DEX_stealth)
        setCheckBox(dexStealthCheckBox, statsData.dex.stealth)
    }

    private fun setupConstitution(view: View) {
        conScoreEditText = view.findViewById(R.id.CON_editText_score)
        setEditText(conScoreEditText, statsData.con.score)

        conModifierEditText = view.findViewById(R.id.CON_editText_mod)
        setEditText(conModifierEditText, statsData.con.modifier)

        conSavingThrowCheckBox = view.findViewById(R.id.CON_savingThrow)
        setCheckBox(conSavingThrowCheckBox, statsData.con.savingThrow)
    }

    private fun setupIntelligence(view: View) {
        intScoreEditText = view.findViewById(R.id.INT_editText_score)
        setEditText(intScoreEditText, statsData.int.score)

        intModifierEditText = view.findViewById(R.id.INT_editText_mod)
        setEditText(intModifierEditText, statsData.int.modifier)

        intSavingThrowCheckBox = view.findViewById(R.id.INT_savingThrow)
        setCheckBox(intSavingThrowCheckBox, statsData.int.savingThrow)

        intArcanaCheckBox = view.findViewById(R.id.INT_arcana)
        setCheckBox(intArcanaCheckBox, statsData.int.arcana)

        intHistoryCheckBox = view.findViewById(R.id.INT_history)
        setCheckBox(intHistoryCheckBox, statsData.int.history)

        intInvestigationCheckBox = view.findViewById(R.id.INT_investigation)
        setCheckBox(intInvestigationCheckBox, statsData.int.investigation)

        intNatureCheckBox = view.findViewById(R.id.INT_nature)
        setCheckBox(intNatureCheckBox, statsData.int.nature)

        intReligionCheckBox = view.findViewById(R.id.INT_religion)
        setCheckBox(intReligionCheckBox, statsData.int.religion)
    }

    private fun setupWisdom(view: View) {
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
    }

    private fun setupCharisma(view: View) {
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

    private fun setupChangeHP(view: View) {
        val changeHP = view.findViewById<EditText>(R.id.changeHP_editText)
        val healButton = view.findViewById<Button>(R.id.HP_add_button)
        healButton.setOnClickListener() {
            val heal = changeHP.text.toString().toInt()
            var hp = hpEditText.text.toString().toInt()
            hp += heal
            hpEditText.setText(hp.toString())
        }

        val damageButton = view.findViewById<Button>(R.id.HP_sub_button)
        damageButton.setOnClickListener() {
            val damage = changeHP.text.toString().toInt()

            val tHPString = tempHPEditText.text.toString()
            var tempHP : Int
            if(tHPString == "") {
                tempHP = 0
            }
            else {
                tempHP = tHPString.toInt()
            }

            var hp = hpEditText.text.toString().toInt()

            if(tempHP > 0) {
                tempHP -= damage
            }
            else {
                hp -= damage
            }

            if(tempHP < 0) {
                hp -= (tempHP * -1)
                tempHP = 0
            }

            if(tempHP == 0) {
                tempHPEditText.text = null
            }
            else {
                tempHPEditText.setText(tempHP.toString())
            }
            hpEditText.setText(hp.toString())
        }
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