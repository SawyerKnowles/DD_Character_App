package com.example.ddcharacterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), OnInputListener {
    private fun addNewCharacterToList(newName: String, newClass: String, newLevel: String) {
        Log.d("Check2", "Activity addNewCharacterToList Called")
        val charLayout = findViewById<LinearLayout>(R.id.charLayout)
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

        charLayout.addView(newCharView)
    }

    override fun sendNewCharacterInfo(newName: String, newClass: String, newLevel: String) {
        Log.d("Check", "Activity sendNewCharacterInfo Called")
        addNewCharacterToList(newName, newClass, newLevel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addCharButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        /* Get Character Info Using Activity
        //Launch InputCharacterInfo activity for results
        var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                //doSomeOperations()
                val nameStr = data?.getStringExtra("name")
                val classStr = data?.getStringExtra("class")
                val levelStr = data?.getStringExtra("level")

                val newCharView = layoutInflater.inflate(R.layout.list_item_character, null)

                val menuName = newCharView.findViewById<TextView>(R.id.mName)
                val menuClass = newCharView.findViewById<TextView>(R.id.mMenuClass)
                val menuLevel = newCharView.findViewById<TextView>(R.id.mMenuLevel)
                menuName.text = nameStr
                menuClass.text = classStr
                menuLevel.text = levelStr

                val characterCard = newCharView.findViewById<CardView>(R.id.characterCard)
                characterCard.setOnClickListener() {
                    Log.d("asdasd", "asdasd")
                }

                charLayout.addView(newCharView)
            }
        }*/

        addCharButton.setOnClickListener() {
            var dialog = NewCharacterDialogFragment()
            dialog.show(supportFragmentManager, "newCharacterDialog")

            /* Start Character Info View Using Activity
            val charInfoPop = Intent(this, InputCharacterInfo::class.java)
            resultLauncher.launch(charInfoPop)
            //startActivity(charInfoPop)*/
        }
    }
}
