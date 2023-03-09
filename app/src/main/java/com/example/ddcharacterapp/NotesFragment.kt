package com.example.ddcharacterapp

import android.os.Bundle
import android.text.Editable
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

    //val notesList = mutableListOf<NoteData>()
    var notesList = ArrayList<NoteData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_notes, container, false)

    }

    override fun onDetach() {
        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.notesData.notesDataList = notesList // set notes list for main activity DM
        Log.d("NotesFragment", "NotesFragment destroyed.")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        Log.d("NotesFragment", "NotesFragment created.")
        val mainAct =  (activity as MainActivity)

        mainAct.supportActionBar?.title = mainAct.characterListGlobal[mainAct.characterIndex].charName

        notesList = mainAct.dataManager.notesData.notesDataList // get notes list from main activity DM


        //notesList = mainAct.getNotesData().notesDataList

        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)
        //val notesList = mutableListOf<NoteData>()
        //recyclerView.adapter = ItemAdapter(notesList, act, this)

        recyclerView.adapter = NotesAdapter(notesList, this)
        //recyclerView.adapter = NotesAdapter(mainAct.dataManager.notesData.notesDataList, this)

        var i = 0

        val notesFAB = act.findViewById<FloatingActionButton>(R.id.notesFAB)
        notesFAB.setOnClickListener() {

            //val newNoteView = layoutInflater.inflate(R.layout.list_item, null)
            //view?.findViewById<RecyclerView>(R.id.notes_recycler)?.addView(newNoteView)
            //notesList.add(NoteData("Note_$i", "body_$i"))
            val body = "Note Body"
            val title = "Note Title"
            //notesList.add(NoteData(Editable.Factory.getInstance().newEditable(title), Editable.Factory.getInstance().newEditable(body), false))
            notesList.add(NoteData(title, body, false))
            i++
            //recyclerView.adapter = ItemAdapter(notesList, act, this)
            recyclerView.adapter = NotesAdapter(notesList, this)
            (recyclerView.adapter as NotesAdapter).notifyItemInserted(notesList.size-1)
            notesList.forEach { Log.d("notesList", it.title.toString()) }
            Log.d("Separator", "---------------")
        }
    }

    // Note Clicked (RecyclerView Item Clicked)
    override fun onItemClick(position: Int) {
        Log.d("Viewholder onClickListener", "Note " + notesList[position].title + " Clicked | Body: " + notesList[position].body)
    }

    fun onSavePressed(position: Int, title : String, body : String) {
        //notesList[position].title = Editable.Factory.getInstance().newEditable(title)
        //notesList[position].body = Editable.Factory.getInstance().newEditable(body)
        notesList[position].title = title
        notesList[position].body = body
        notesList[position].titleSaved = true
        notesList[position].bodySaved = true
        updateAdapter()
    }

    fun onDeletePressed(position: Int) {
        notesList.removeAt(position)
        updateAdapter()
    }

    fun updateAdapter() {
        val act = (activity as AppCompatActivity)
        val recyclerView = act.findViewById<RecyclerView>(R.id.notes_recycler)
        recyclerView.adapter = NotesAdapter(notesList, this)
    }

}