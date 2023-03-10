package com.example.ddcharacterapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.adapter.NotesAdapter
import com.example.ddcharacterapp.data.NoteData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment(), RecyclerViewInterface {

    private var notesList = ArrayList<NoteData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onDetach() {
        Log.d("NotesFragment", "NotesFragment destroyed.")
        super.onDetach()
    }

    override fun onPause() {
        Log.d("NotesFragment", "onPause called.")
        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.notesData.notesDataList = notesList // set notes list for main activity DM
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("NotesFragment", "NotesFragment created.")
        val mainAct =  (activity as MainActivity)

        mainAct.supportActionBar?.title = mainAct.dataManager.traitsData.basicData.name

        notesList = mainAct.dataManager.notesData.notesDataList // get notes list from main activity DM

        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)

        recyclerView.adapter = NotesAdapter(notesList, this)

        var i = 0

        val notesFAB = act.findViewById<FloatingActionButton>(R.id.notesFAB)
        notesFAB.setOnClickListener() {

            val body = "Note Body"
            val title = "Note Title"
            notesList.add(NoteData(title, body, false))
            i++
            recyclerView.adapter = NotesAdapter(notesList, this)
            (recyclerView.adapter as NotesAdapter).notifyItemInserted(notesList.size-1)
        }
    }

    // Note Clicked (RecyclerView Item Clicked)
    override fun onItemClick(position: Int) {
        Log.d("Viewholder onClickListener", "Note " + notesList[position].title + " Clicked | Body: " + notesList[position].body)
    }

    fun onDeletePressed(position: Int) {
        notesList.removeAt(position)
        updateAdapter()
    }

    private fun updateAdapter() {
        val act = (activity as AppCompatActivity)
        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)
        recyclerView.adapter = NotesAdapter(notesList, this)
    }

}