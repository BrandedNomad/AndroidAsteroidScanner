package com.example.asteroidscanner.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidscanner.R

class MainAdapter(
    var asteroidList:ArrayList<String>
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    //Create the viewHolder class
    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.mainListItemName)
        val textViewDate: TextView = itemView.findViewById(R.id.mainListItemDate)
        val imageViewIcon: ImageView = itemView.findViewById(R.id.mainListItemImage)
    }

    //Creates the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.main_list_item,
                parent,
                false
            )

        return MainViewHolder(view)
    }

    //Binds data to the viewholder
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.textViewName.text = asteroidList[position]
        holder.textViewDate.text = asteroidList[position]
    }

    //tells the adapter the size of the data
    override fun getItemCount(): Int {
        return asteroidList.size
    }
}