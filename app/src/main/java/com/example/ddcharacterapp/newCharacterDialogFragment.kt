package com.example.ddcharacterapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

interface OnInputListener {
    fun sendNewCharacterInfo(newName: String, newClass: String, newLevel: String)
}

class NewCharacterDialogFragment: DialogFragment() {

    lateinit var inputListener: OnInputListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View = inflater.inflate(R.layout.new_character_dialog, container, false)

        val cancelButton = rootView.findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener() {
            dismiss()
        }

        val addButton = rootView.findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener() {
            val newName = rootView.findViewById<TextView>(R.id.new_character_name).text.toString()
            val newClass = rootView.findViewById<TextView>(R.id.new_character_class).text.toString()
            val newLevel = rootView.findViewById<TextView>(R.id.new_character_level).text.toString()
            inputListener.sendNewCharacterInfo(newName, newClass, newLevel)
            dismiss()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {

        } catch (e: ClassCastException) {
            Log.d("ClassCastExceptionDetected", "Activity doesn't implement the onInputListener interface")
        }
    }
}