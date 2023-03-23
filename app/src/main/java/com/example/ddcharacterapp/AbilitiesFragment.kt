package com.example.ddcharacterapp

import android.os.Bundle
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
        return inflater.inflate(R.layout.fragment_abilities, container, false)
    }

    override fun onDetach() {
        Log.d("AbilityFragment", "AbilityFragment destroyed.")
        super.onDetach()
    }

    override fun onPause() {
        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.abilitiesData.abilitiesDataList = abilityList // set ability list for main activity DM
        Log.d("AbilityFragment", "onPause called.")
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("AbilityFragment", "AbilityFragment created.")
        val mainAct =  (activity as MainActivity)

        abilityList = mainAct.dataManager.abilitiesData.abilitiesDataList // get abilities list from main activity DM

        val recyclerView = act.findViewById<RecyclerView>(R.id.abilities_recycler)

        recyclerView.adapter = AbilityAdapter(abilityList, this)

        var i = 0

        val inventoryFAB = act.findViewById<FloatingActionButton>(R.id.abilitiesFAB)
        inventoryFAB.setOnClickListener() {
            val body = "Ability Description"
            val title = "Ability Title"
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

    private fun updateAdapter() {
        val act = (activity as AppCompatActivity)
        val recyclerView = act.findViewById<RecyclerView>(R.id.abilities_recycler)
        recyclerView.adapter = AbilityAdapter(abilityList, this)
    }
}