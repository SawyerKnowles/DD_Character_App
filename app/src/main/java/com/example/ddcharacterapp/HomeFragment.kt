package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), OnInputListener {

    private fun addNewCharacterToList(newName: String, newClass: String, newLevel: String) {
        Log.d("Check2", "Activity addNewCharacterToList Called")
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
        }

        view?.findViewById<LinearLayout>(R.id.charLayout)?.addView(newCharView)
    }

    override fun sendNewCharacterInfo(newName: String, newClass: String, newLevel: String) {
        Log.d("Check", "Activity sendNewCharacterInfo Called")
        addNewCharacterToList(newName, newClass, newLevel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "D&D Character App"


        val addCharButton = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addCharButton?.setOnClickListener() {
            var dialog = NewCharacterDialogFragment()
            //dialog.show(supportFragmentManager, "newCharacterDialog")
            dialog.show(
                (activity as AppCompatActivity).supportFragmentManager,
                "newCharacterDialog"
            )
        }
    }
}