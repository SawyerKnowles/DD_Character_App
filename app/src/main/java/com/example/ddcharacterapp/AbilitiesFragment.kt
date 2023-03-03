package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.AbilityAdapter
import com.example.ddcharacterapp.adapter.InventoryAdapter
import com.example.ddcharacterapp.adapter.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AbilitiesFragment : Fragment() {

    var abilityList = mutableListOf<AbilityData>()

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
        recyclerView.adapter = AbilityAdapter(abilityList, this)

        var i = 0

        val inventoryFAB = act.findViewById<FloatingActionButton>(R.id.abilitiesFAB)
        inventoryFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            val body = "Ability Description"
            val title = "Ability Title"
            abilityList.add(AbilityData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))
            i++
            recyclerView.adapter = AbilityAdapter(abilityList, this)

            abilityList.forEach { Log.d("notesList", it.title.toString()) }
            Log.d("Separator", "---------------")
        }

    }

    fun onDeletePressed(position: Int) {
        abilityList.removeAt(position)
        updateAdapter()
    }

    fun updateAdapter() {
        val act = (activity as AppCompatActivity)
        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)
        recyclerView.adapter = AbilityAdapter(abilityList, this)
    }
}