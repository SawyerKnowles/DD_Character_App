package com.example.ddcharacterapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ddcharacterapp.CharacterCardData
import com.example.ddcharacterapp.MainActivity
import com.example.ddcharacterapp.R

class CharacterCardAdapter(private var characterList: ArrayList<CharacterCardData>, var activity : AppCompatActivity) : RecyclerView.Adapter<CharacterCardAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterCardAdapter.ViewHolder {
        //create new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent,false)
        return CharacterCardAdapter.ViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(characterList[position]){
                // set name of the language from the list
                view.findViewById<TextView>(R.id.mName).text = this.charName
                // set description to the text
                // since this is inside "expandedView" its visibility will be gone initially
                // after click on the item we will make the visibility of the "expandedView" visible
                // which will also make the visibility of desc also visible
                view.findViewById<TextView>(R.id.mMenuClass).text = this.charClass
                view.findViewById<TextView>(R.id.mMenuLevel).text = this.charLevel
                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                //view.findViewById<RelativeLayout>(R.id.expanded_view).visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                view.findViewById<CardView>(R.id.characterCard).setOnClickListener {
                    //this.expand = !this.expand
                    Log.d("Character Card Click", "Character " + this.charName + " card clicked")
                    (activity as MainActivity).dataManager = this.charDataManager // set main activity DM to selected Character's DM
                    Log.d("check", (activity as MainActivity).dataManager.toString())
                    (activity as MainActivity).characterIndex = adapterPosition
                    activity.addMenuProvider((activity) as MenuProvider)
                    view.findNavController().navigate(R.id.destination_stats)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}