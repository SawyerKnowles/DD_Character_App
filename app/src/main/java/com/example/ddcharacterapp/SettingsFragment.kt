package com.example.ddcharacterapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(), OnDeleteInputListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        val mainAct = (activity as MainActivity) // get activity

        val deleteButton = act.findViewById<Button>(R.id.delete_character_button)
        deleteButton.setOnClickListener() {
            val dialog = DeleteCharacterDialogFragment()
            dialog.inputListener = this
            dialog.show(
                (activity as AppCompatActivity).supportFragmentManager,
                "newCharacterDialog"
            )
        }
    }

    override fun confirmDelete() {
        val mainAct = (activity as MainActivity)
        mainAct.characterListGlobal.removeAt(mainAct.characterIndex)
        Toast.makeText(mainAct.applicationContext, "Character Deleted.", Toast.LENGTH_SHORT).show()
        mainAct.onSupportNavigateUp()
    }
}