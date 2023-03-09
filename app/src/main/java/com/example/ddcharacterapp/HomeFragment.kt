package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.CharacterCardAdapter
import com.example.ddcharacterapp.adapter.InventoryAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), OnInputListener {

    //val characterList = ArrayList<CharacterCardData>()\
    lateinit var characterList : ArrayList<CharacterCardData>
    lateinit var recyclerView : RecyclerView
    //lateinit var adapter : CharacterCardAdapter

    private fun addNewCharacterToList(newName: String, newClass: String, newLevel: String) {
        Log.d("Check2", "Fragment addNewCharacterToList Called")
        val newCharView = layoutInflater.inflate(R.layout.list_item_character, null)

        val menuName = newCharView.findViewById<TextView>(R.id.mName)
        val menuClass = newCharView.findViewById<TextView>(R.id.mMenuClass)
        val menuLevel = newCharView.findViewById<TextView>(R.id.mMenuLevel)
        menuName.text = newName
        menuClass.text = newClass
        menuLevel.text = newLevel

        val characterCard = newCharView.findViewById<CardView>(R.id.characterCard)
        characterCard.setOnClickListener() {
            Log.d("CharacterName", "Character Name: " + menuName.text.toString())
            Log.d("CharacterClass", "Character Class: " + menuClass.text.toString())
            Log.d("CharacterLevel", "Character Level: " + menuLevel.text.toString())
            //activity?.addMenuProvider(activity as MenuProvider)
            //Navigation.findNavController(it).navigate(R.id.destination_stats)
        }

        //view?.findViewById<LinearLayout>(R.id.charLayout)?.addView(newCharView)
        val characterDataManager = DataManager()
        characterDataManager.traitsData.basicData.name = menuName.text.toString()
        characterDataManager.traitsData.basicData.characterClass = menuClass.text.toString()
        characterDataManager.traitsData.basicData.level = menuLevel.text.toString()
        characterList.add(CharacterCardData(menuName.text.toString(), menuClass.text.toString(), menuLevel.text.toString(), characterDataManager))
        val act = (activity as AppCompatActivity) // get activity
        recyclerView.adapter = CharacterCardAdapter(characterList, act)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun sendNewCharacterInfo(newName: String, newClass: String, newLevel: String) {
        Log.d("Check", "Fragment sendNewCharacterInfo Called")
        addNewCharacterToList(newName, newClass, newLevel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        Log.d("HomeFragment", "HomeFragment created.")
        characterList = (activity as MainActivity).characterListGlobal
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "D&D Character App"

        recyclerView = act.findViewById<RecyclerView>(R.id.character_recycler)
        recyclerView.adapter = CharacterCardAdapter(characterList, act)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val addCharButton = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addCharButton?.setOnClickListener() {
            val dialog = NewCharacterDialogFragment()
            //dialog.show(supportFragmentManager, "newCharacterDialog")
            dialog.inputListener = this
            dialog.show(
                (activity as AppCompatActivity).supportFragmentManager,
                "newCharacterDialog"
            )
        }
    }

    override fun onDetach() {
        Log.d("HomeFragment", "HomeFragment destroyed.")
        super.onDetach()
    }

}