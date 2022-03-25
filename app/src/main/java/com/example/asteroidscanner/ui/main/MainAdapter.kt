package com.example.asteroidscanner.ui.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidscanner.R

class MainAdapter(
    var asteroidList:ArrayList<String>?
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    //Create the viewHolder class
    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.mainListItemName)
        val textViewDate: TextView = itemView.findViewById(R.id.mainListItemDate)
        val imageViewIcon: ImageView = itemView.findViewById(R.id.mainListItemImage)

        fun bind(
            holder:MainViewHolder,
            item: String?,
        ){
            holder.textViewName.text = item
            holder.textViewDate.text = item
            //holder.imageViewIcon.setImageURI(item)

        }

        companion object {
            fun from(parent:ViewGroup):MainViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.main_list_item,
                        parent,
                        false
                    )

                return MainViewHolder(view)
            }
        }
    }

    //Creates the viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    //Binds data to the viewholder
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        var item = asteroidList?.get(position)
        holder.bind(holder,item)

    }

    //tells the adapter the size of the data
    override fun getItemCount(): Int {
        return asteroidList?.size!!
    }
}

class AsteroidDiffCallback: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }

}