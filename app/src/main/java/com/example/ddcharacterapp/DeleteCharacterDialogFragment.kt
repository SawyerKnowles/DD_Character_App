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


interface OnDeleteInputListener {
    fun confirmDelete()
}

class DeleteCharacterDialogFragment : DialogFragment() {

    public lateinit var inputListener: OnDeleteInputListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.delete_character_dialog, container, false)

        val cancelButton = rootView.findViewById<Button>(R.id.delete_cancel_button)
        cancelButton.setOnClickListener() {
            dismiss()
        }

        val confirmButton = rootView.findViewById<Button>(R.id.delete_confirm_button)
        confirmButton.setOnClickListener() {
            inputListener.confirmDelete()
            dismiss()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            //inputListener = activity as OnInputListener
            //inputListener = targetFragment as OnInputListener
        } catch (e: ClassCastException) {
            Log.d("ClassCastExceptionDetected", "Activity doesn't implement the onInputListener interface")
        }
    }

}