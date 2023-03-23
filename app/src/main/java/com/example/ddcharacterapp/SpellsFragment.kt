package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.SpellAdapter
import com.example.ddcharacterapp.data.SpellData

class SpellsFragment : Fragment() {

    private var level_0_spell_list = ArrayList<SpellData>()
    private var level_1_spell_list = ArrayList<SpellData>()
    private var level_2_spell_list = ArrayList<SpellData>()
    private var level_3_spell_list = ArrayList<SpellData>()
    private var level_4_spell_list = ArrayList<SpellData>()
    private var level_5_spell_list = ArrayList<SpellData>()
    private var level_6_spell_list = ArrayList<SpellData>()
    private var level_7_spell_list = ArrayList<SpellData>()
    private var level_8_spell_list = ArrayList<SpellData>()
    private var level_9_spell_list = ArrayList<SpellData>()

    private var castingAbility = ""
    private var saveDC = ""
    private var attackBonus = ""

    private lateinit var castingAbilityEditText : EditText
    private lateinit var saveDCEditText: EditText
    private lateinit var attackBonusEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onDetach() {
        Log.d("SpellsFragment", "SpellsFragment destroyed.")
        super.onDetach()
    }

    override fun onPause() {
        Log.d("SpellsFragment", "onPause Called.")
        writeToDataManager()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("SpellsFragment", "SpellsFragment created.")
        val mainAct =  (activity as MainActivity)

        readFromDataManager()

        //Set up casting ability edit text saving
        castingAbilityEditText = view.findViewById<EditText>(R.id.casting_ability_editText)
        castingAbilityEditText.isSaveEnabled = false
        castingAbilityEditText.setText(castingAbility)


        //Set up saveDC edit text saving
        saveDCEditText = view.findViewById<EditText>(R.id.saveDC_editText)
        saveDCEditText.isSaveEnabled = false
        saveDCEditText.setText(saveDC)

        //Set up attack bonus edit text saving
        attackBonusEditText = view.findViewById<EditText>(R.id.attack_bonus_editText)
        attackBonusEditText.isSaveEnabled = false
        attackBonusEditText.setText(attackBonus)


        val level_0_recycler_view = attachRecyclerList(act, level_0_spell_list, R.id.level_0_spells_recycler)
        val level_1_recycler_view = attachRecyclerList(act, level_1_spell_list, R.id.level_1_spells_recycler)
        val level_2_recycler_view = attachRecyclerList(act, level_2_spell_list, R.id.level_2_spells_recycler)
        val level_3_recycler_view = attachRecyclerList(act, level_3_spell_list, R.id.level_3_spells_recycler)
        val level_4_recycler_view = attachRecyclerList(act, level_4_spell_list, R.id.level_4_spells_recycler)
        val level_5_recycler_view = attachRecyclerList(act, level_5_spell_list, R.id.level_5_spells_recycler)
        val level_6_recycler_view = attachRecyclerList(act, level_6_spell_list, R.id.level_6_spells_recycler)
        val level_7_recycler_view = attachRecyclerList(act, level_7_spell_list, R.id.level_7_spells_recycler)
        val level_8_recycler_view = attachRecyclerList(act, level_8_spell_list, R.id.level_8_spells_recycler)
        val level_9_recycler_view = attachRecyclerList(act, level_9_spell_list, R.id.level_9_spells_recycler)

        val body = "Spell Description"
        val title = "Spell Title"

        val level_0_add_button = act.findViewById<Button>(R.id.level_0_spells_add_button)
        level_0_add_button.setOnClickListener() {
            level_0_spell_list.add(SpellData(title, body, false))
            if (level_0_recycler_view != null) {
                level_0_recycler_view.adapter = SpellAdapter(level_0_spell_list, this)
            }
            if (level_0_recycler_view != null) {
                level_0_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_1_add_button = act.findViewById<Button>(R.id.level_1_spells_add_button)
        level_1_add_button.setOnClickListener() {
            level_1_spell_list.add(SpellData(title, body, false))
            if (level_1_recycler_view != null) {
                level_1_recycler_view.adapter = SpellAdapter(level_1_spell_list, this)
            }
            if (level_1_recycler_view != null) {
                level_1_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_2_add_button = act.findViewById<Button>(R.id.level_2_spells_add_button)
        level_2_add_button.setOnClickListener() {
            level_2_spell_list.add(SpellData(title, body, false))
            if (level_2_recycler_view != null) {
                level_2_recycler_view.adapter = SpellAdapter(level_2_spell_list, this)
            }
            if (level_2_recycler_view != null) {
                level_2_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_3_add_button = act.findViewById<Button>(R.id.level_3_spells_add_button)
        level_3_add_button.setOnClickListener() {
            level_3_spell_list.add(SpellData(title, body, false))
            if (level_3_recycler_view != null) {
                level_3_recycler_view.adapter = SpellAdapter(level_3_spell_list, this)
            }
            if (level_3_recycler_view != null) {
                level_3_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_4_add_button = act.findViewById<Button>(R.id.level_4_spells_add_button)
        level_4_add_button.setOnClickListener() {
            level_4_spell_list.add(SpellData(title, body, false))
            if (level_4_recycler_view != null) {
                level_4_recycler_view.adapter = SpellAdapter(level_4_spell_list, this)
            }
            if (level_4_recycler_view != null) {
                level_4_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_5_add_button = act.findViewById<Button>(R.id.level_5_spells_add_button)
        level_5_add_button.setOnClickListener() {
            level_5_spell_list.add(SpellData(title, body, false))
            if (level_5_recycler_view != null) {
                level_5_recycler_view.adapter = SpellAdapter(level_5_spell_list, this)
            }
            if (level_5_recycler_view != null) {
                level_5_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_6_add_button = act.findViewById<Button>(R.id.level_6_spells_add_button)
        level_6_add_button.setOnClickListener() {
            level_6_spell_list.add(SpellData(title, body, false))
            if (level_6_recycler_view != null) {
                level_6_recycler_view.adapter = SpellAdapter(level_6_spell_list, this)
            }
            if (level_6_recycler_view != null) {
                level_6_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_7_add_button = act.findViewById<Button>(R.id.level_7_spells_add_button)
        level_7_add_button.setOnClickListener() {
            level_7_spell_list.add(SpellData(title, body, false))
            if (level_7_recycler_view != null) {
                level_7_recycler_view.adapter = SpellAdapter(level_7_spell_list, this)
            }
            if (level_7_recycler_view != null) {
                level_7_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }


        val level_8_add_button = act.findViewById<Button>(R.id.level_8_spells_add_button)
        level_8_add_button.setOnClickListener() {
            level_8_spell_list.add(SpellData(title, body, false))
            if (level_8_recycler_view != null) {
                level_8_recycler_view.adapter = SpellAdapter(level_8_spell_list, this)
            }
            if (level_8_recycler_view != null) {
                level_8_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_9_add_button = act.findViewById<Button>(R.id.level_9_spells_add_button)
        level_9_add_button.setOnClickListener() {
            level_9_spell_list.add(SpellData(title, body, false))
            if (level_9_recycler_view != null) {
                level_9_recycler_view.adapter = SpellAdapter(level_9_spell_list, this)
            }
            if (level_9_recycler_view != null) {
                level_9_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }
    }

    private fun attachRecyclerList(act: AppCompatActivity, list : ArrayList<SpellData>, recycler : Int): RecyclerView? {
        var recyclerView = act.findViewById<RecyclerView>(recycler)
        recyclerView.adapter = SpellAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return recyclerView
    }

    fun updateAdapters() {
        val act = (activity as AppCompatActivity)

        val recyclerView0 = act.findViewById<RecyclerView>(R.id.level_0_spells_recycler)
        recyclerView0.adapter = SpellAdapter(level_0_spell_list, this)

        val recyclerView1 = act.findViewById<RecyclerView>(R.id.level_1_spells_recycler)
        recyclerView1.adapter = SpellAdapter(level_1_spell_list, this)

        val recyclerView2 = act.findViewById<RecyclerView>(R.id.level_2_spells_recycler)
        recyclerView2.adapter = SpellAdapter(level_2_spell_list, this)

        val recyclerView3 = act.findViewById<RecyclerView>(R.id.level_3_spells_recycler)
        recyclerView3.adapter = SpellAdapter(level_3_spell_list, this)

        val recyclerView4 = act.findViewById<RecyclerView>(R.id.level_4_spells_recycler)
        recyclerView4.adapter = SpellAdapter(level_4_spell_list, this)

        val recyclerView5 = act.findViewById<RecyclerView>(R.id.level_5_spells_recycler)
        recyclerView5.adapter = SpellAdapter(level_5_spell_list, this)

        val recyclerView6 = act.findViewById<RecyclerView>(R.id.level_6_spells_recycler)
        recyclerView6.adapter = SpellAdapter(level_6_spell_list, this)

        val recyclerView7 = act.findViewById<RecyclerView>(R.id.level_7_spells_recycler)
        recyclerView7.adapter = SpellAdapter(level_7_spell_list, this)

        val recyclerView8 = act.findViewById<RecyclerView>(R.id.level_8_spells_recycler)
        recyclerView8.adapter = SpellAdapter(level_8_spell_list, this)

        val recyclerView9 = act.findViewById<RecyclerView>(R.id.level_9_spells_recycler)
        recyclerView9.adapter = SpellAdapter(level_9_spell_list, this)
    }

    private fun readFromDataManager() {
        val mainAct =  (activity as MainActivity)

        castingAbility = mainAct.dataManager.spellsData.castingAbility
        saveDC = mainAct.dataManager.spellsData.saveDC
        attackBonus = mainAct.dataManager.spellsData.attackBonus

        level_0_spell_list = mainAct.dataManager.spellsData.level_0_spell_dataList // get spell list from main activity DM
        level_1_spell_list = mainAct.dataManager.spellsData.level_1_spell_dataList // get spell list from main activity DM
        level_2_spell_list = mainAct.dataManager.spellsData.level_2_spell_dataList // get spell list from main activity DM
        level_3_spell_list = mainAct.dataManager.spellsData.level_3_spell_dataList // get spell list from main activity DM
        level_4_spell_list = mainAct.dataManager.spellsData.level_4_spell_dataList // get spell list from main activity DM
        level_5_spell_list = mainAct.dataManager.spellsData.level_5_spell_dataList // get spell list from main activity DM
        level_6_spell_list = mainAct.dataManager.spellsData.level_6_spell_dataList // get spell list from main activity DM
        level_7_spell_list = mainAct.dataManager.spellsData.level_7_spell_dataList // get spell list from main activity DM
        level_8_spell_list = mainAct.dataManager.spellsData.level_8_spell_dataList // get spell list from main activity DM
        level_9_spell_list = mainAct.dataManager.spellsData.level_9_spell_dataList // get spell list from main activity DM

    }

    private fun writeToDataManager() {
        val mainAct =  (activity as MainActivity)
        castingAbility = castingAbilityEditText.text.toString()
        mainAct.dataManager.spellsData.castingAbility = castingAbility

        saveDC = saveDCEditText.text.toString()
        mainAct.dataManager.spellsData.saveDC = saveDC

        attackBonus = attackBonusEditText.text.toString()
        mainAct.dataManager.spellsData.attackBonus = attackBonus

        mainAct.dataManager.spellsData.level_0_spell_dataList = level_0_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_1_spell_dataList = level_1_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_2_spell_dataList = level_2_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_3_spell_dataList = level_3_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_4_spell_dataList = level_4_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_5_spell_dataList = level_5_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_6_spell_dataList = level_6_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_7_spell_dataList = level_7_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_8_spell_dataList = level_8_spell_list // set spell list for main activity DM
        mainAct.dataManager.spellsData.level_9_spell_dataList = level_9_spell_list // set spell list for main activity DM
    }
}