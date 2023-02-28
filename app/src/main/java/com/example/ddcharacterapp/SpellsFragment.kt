package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.NotesAdapter
import com.example.ddcharacterapp.adapter.SpellAdapter

class SpellsFragment : Fragment() {

    val level_0_spell_list = mutableListOf<SpellData>()

    val level_1_spell_list = mutableListOf<SpellData>()
    val level_2_spell_list = mutableListOf<SpellData>()
    val level_3_spell_list = mutableListOf<SpellData>()
    val level_4_spell_list = mutableListOf<SpellData>()
    val level_5_spell_list = mutableListOf<SpellData>()
    val level_6_spell_list = mutableListOf<SpellData>()
    val level_7_spell_list = mutableListOf<SpellData>()
    val level_8_spell_list = mutableListOf<SpellData>()
    val level_9_spell_list = mutableListOf<SpellData>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_spells, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "Character Name"

        /*
        val level_0_recycler_view = act.findViewById<RecyclerView>(R.id.level_0_spells_recycler)
        level_0_recycler_view.adapter = SpellAdapter(level_0_spell_list)
        level_0_recycler_view.layoutManager = LinearLayoutManager(context)

         */

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


        val level_0_add_button = act.findViewById<Button>(R.id.level_0_spells_add_button)
        level_0_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_0_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_0_recycler_view != null) {
                level_0_recycler_view.adapter = SpellAdapter(level_0_spell_list)
            }
            if (level_0_recycler_view != null) {
                level_0_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_1_add_button = act.findViewById<Button>(R.id.level_1_spells_add_button)
        level_1_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_1_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_1_recycler_view != null) {
                level_1_recycler_view.adapter = SpellAdapter(level_1_spell_list)
            }
            if (level_1_recycler_view != null) {
                level_1_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_2_add_button = act.findViewById<Button>(R.id.level_2_spells_add_button)
        level_2_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_2_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_2_recycler_view != null) {
                level_2_recycler_view.adapter = SpellAdapter(level_2_spell_list)
            }
            if (level_2_recycler_view != null) {
                level_2_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_3_add_button = act.findViewById<Button>(R.id.level_3_spells_add_button)
        level_3_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_3_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_3_recycler_view != null) {
                level_3_recycler_view.adapter = SpellAdapter(level_3_spell_list)
            }
            if (level_3_recycler_view != null) {
                level_3_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_4_add_button = act.findViewById<Button>(R.id.level_4_spells_add_button)
        level_4_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_4_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_4_recycler_view != null) {
                level_4_recycler_view.adapter = SpellAdapter(level_4_spell_list)
            }
            if (level_4_recycler_view != null) {
                level_4_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_5_add_button = act.findViewById<Button>(R.id.level_5_spells_add_button)
        level_5_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_5_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_5_recycler_view != null) {
                level_5_recycler_view.adapter = SpellAdapter(level_5_spell_list)
            }
            if (level_5_recycler_view != null) {
                level_5_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_6_add_button = act.findViewById<Button>(R.id.level_6_spells_add_button)
        level_6_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_6_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_6_recycler_view != null) {
                level_6_recycler_view.adapter = SpellAdapter(level_6_spell_list)
            }
            if (level_6_recycler_view != null) {
                level_6_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_7_add_button = act.findViewById<Button>(R.id.level_7_spells_add_button)
        level_7_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_7_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_7_recycler_view != null) {
                level_7_recycler_view.adapter = SpellAdapter(level_7_spell_list)
            }
            if (level_7_recycler_view != null) {
                level_7_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }


        val level_8_add_button = act.findViewById<Button>(R.id.level_8_spells_add_button)
        level_8_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_8_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_8_recycler_view != null) {
                level_8_recycler_view.adapter = SpellAdapter(level_8_spell_list)
            }
            if (level_8_recycler_view != null) {
                level_8_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }

        val level_9_add_button = act.findViewById<Button>(R.id.level_9_spells_add_button)
        level_9_add_button.setOnClickListener() {
            val e = "Spell Body"
            level_9_spell_list.add(SpellData("New Spell", Editable.Factory.getInstance().newEditable(e), false))
            if (level_9_recycler_view != null) {
                level_9_recycler_view.adapter = SpellAdapter(level_9_spell_list)
            }
            if (level_9_recycler_view != null) {
                level_9_recycler_view.layoutManager = LinearLayoutManager(context)
            }

        }
    }

    private fun attachRecyclerList(act: AppCompatActivity, list : List<SpellData>, recycler : Int): RecyclerView? {
        var recyclerView = act.findViewById<RecyclerView>(recycler)
        recyclerView.adapter = SpellAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.isNestedScrollingEnabled = true
        return recyclerView
    }
}