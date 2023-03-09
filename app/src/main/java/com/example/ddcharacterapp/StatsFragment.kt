package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ddcharacterapp.data.*

class StatsFragment : Fragment() {

    var healthData = HealthData("","","","","")
    var STRData = StrengthData("","", false, false)
    var DEXData = DexterityData("","",false, false, false, false)
    var CONData = ConstitutionData("","",false)
    var INTData = IntelligenceData("","",false, false,false,false,false,false)
    var WISData = WisdomData("","",false,false,false,false,false,false)
    var CHAData = CharismaData("", "", false,false,false,false,false)
    var statsData = StatsData(healthData, "", "", "", "", STRData, DEXData, CONData, INTData, WISData, CHAData)

    lateinit var maxHPEditText: EditText
    lateinit var tempHPEditText: EditText
    lateinit var hpEditText: EditText
    lateinit var hdEditText : EditText
    lateinit var maxHDEditText : EditText
    lateinit var acEditText : EditText
    lateinit var speedEditText : EditText
    lateinit var initEditText : EditText
    lateinit var ppEditText : EditText


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
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onDetach() {
        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.statsData = statsData
        Log.d("StatsFragment", "StatsFragment destroyed.")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("StatsFragment", "StatsFragment created.")
        val mainAct =  (activity as MainActivity)
        mainAct.supportActionBar?.title = mainAct.characterListGlobal[mainAct.characterIndex].charName



        statsData = mainAct.dataManager.statsData

        maxHPEditText = view.findViewById<EditText>(R.id.maxHP_editText)


        tempHPEditText = view.findViewById<EditText>(R.id.tempHP_editText)


        hpEditText = view.findViewById<EditText>(R.id.HP_editText)


        hdEditText = view.findViewById<EditText>(R.id.HD_editText)


        maxHDEditText = view.findViewById<EditText>(R.id.maxHD_editText)


        acEditText = view.findViewById<EditText>(R.id.AC_editText)


        speedEditText = view.findViewById<EditText>(R.id.speed_editText)


        initEditText = view.findViewById<EditText>(R.id.INIT_editText)


        ppEditText = view.findViewById<EditText>(R.id.PP_editText)


    }

    private fun readFromDataManager() {

    }

    private fun writeToDataManager() {

    }



}