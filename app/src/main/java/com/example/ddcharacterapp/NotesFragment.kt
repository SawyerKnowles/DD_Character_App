package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.ItemAdapter
import com.example.ddcharacterapp.adapter.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment(), RecyclerViewInterface {
    val notesList = mutableListOf<NoteData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_notes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "Character Name"

        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)
        //val notesList = mutableListOf<NoteData>()
        //recyclerView.adapter = ItemAdapter(notesList, act, this)
        recyclerView.adapter = NotesAdapter(notesList)

        var i = 0

        val notesFAB = act.findViewById<FloatingActionButton>(R.id.notesFAB)
        notesFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            //notesList.add(NoteData("Note_$i", "body_$i"))
            val e = "Item Body"
            notesList.add(NoteData("Item_$i", Editable.Factory.getInstance().newEditable(e), false))
            i++
            //recyclerView.adapter = ItemAdapter(notesList, act, this)
            recyclerView.adapter = NotesAdapter(notesList)
        }
    }

    // Note Clicked (RecyclerView Item Clicked)
    override fun onItemClick(position: Int) {
        Log.d("Viewholder onClickListener", "Note " + notesList[position].title + " Clicked | Body: " + notesList[position].body)
    }
}