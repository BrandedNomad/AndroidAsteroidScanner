package com.example.asteroidscanner.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidscanner.R
import com.example.asteroidscanner.domain.Asteroid


class MainAdapter(val onClickListener: OnClickListener): ListAdapter<Asteroid, MainAdapter.MainViewHolder>(AsteroidDiffCallback()) {

    //Create the viewHolder class
    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textViewName: TextView = itemView.findViewById(R.id.mainListItemName)
        val textViewDate: TextView = itemView.findViewById(R.id.mainListItemDate)
        val imageViewIcon: ImageView = itemView.findViewById(R.id.mainListItemImage)

        fun bind(
            holder:MainViewHolder,
            item: Asteroid?,
        ){
            holder.textViewName.text = item?.name
            holder.textViewDate.text = item?.date
            holder.imageViewIcon.setImageResource(
                when(item?.status){
                    false -> R.drawable.ic_status_normal
                    true -> R.drawable.ic_status_potentially_hazardous
                    else -> {
                        R.drawable.ic_status_normal
                    }
                }
            )
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

        //var item = asteroidList?.get(position)
        var item = getItem(position)
        holder.bind(holder,item)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }

    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Asteroid]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Asteroid]
     */
    class OnClickListener(val clickListener: (asteroid:Asteroid) -> Unit){
        fun onClick(asteroid:Asteroid) = clickListener(asteroid)
    }

}

class AsteroidDiffCallback: DiffUtil.ItemCallback<Asteroid>(){

    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem ==newItem
    }

}