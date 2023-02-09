package com.example.ddcharacterapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addCharButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val charLayout = findViewById<LinearLayout>(R.id.charLayout)

        //val newCharView = layoutInflater.inflate(R.layout.list_item_character, null)
        //charLayout.addView(newCharView)

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
        }
        addCharButton.setOnClickListener() {
            val charInfoPop = Intent(this, InputCharacterInfo::class.java)
            resultLauncher.launch(charInfoPop)
            //startActivity(charInfoPop)
        }
    }
}
