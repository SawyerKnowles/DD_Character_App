package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.AbilityAdapter
import com.example.ddcharacterapp.data.AbilityData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AbilitiesFragment : Fragment() {

    var abilityList = ArrayList<AbilityData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_abilities, container, false)
    }

    override fun onDetach() {

        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.abilitiesData.abilitiesDataList = abilityList // set ability list for main activity DM
        Log.d("AbilityFragment", "AbilityFragment destroyed.")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("AbilityFragment", "AbilityFragment created.")
        val mainAct =  (activity as MainActivity)

        mainAct.supportActionBar?.title = mainAct.dataManager.traitsData.basicData.name

        abilityList = mainAct.dataManager.abilitiesData.abilitiesDataList // get abilities list from main activity DM

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
            //abilityList.add(AbilityData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))
            abilityList.add(AbilityData(title, body, false))
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
        val recyclerView = act.findViewById<RecyclerView>(R.id.abilities_recycler)
        recyclerView.adapter = AbilityAdapter(abilityList, this)
    }
}