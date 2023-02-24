package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.AbilityAdapter
import com.example.ddcharacterapp.adapter.InventoryAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AbilitiesFragment : Fragment() {

    val abilityList = mutableListOf<AbilityData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_abilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "Character Name"

        val recyclerView = act.findViewById<RecyclerView>(R.id.abilities_recycler)
        //val notesList = mutableListOf<NoteData>()
        recyclerView.adapter = AbilityAdapter(abilityList)

        var i = 0

        val inventoryFAB = act.findViewById<FloatingActionButton>(R.id.abilitiesFAB)
        inventoryFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            val e = "Item Body"
            abilityList.add(AbilityData("Item_$i", Editable.Factory.getInstance().newEditable(e), false))
            i++
            recyclerView.adapter = AbilityAdapter(abilityList)
        }

    }
}