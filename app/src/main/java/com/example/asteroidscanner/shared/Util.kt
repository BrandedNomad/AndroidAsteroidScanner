package com.example.asteroidscanner.shared


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.asteroidscanner.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    companion object {

        fun getCurrentDate():String{
            val date = Calendar.getInstance().timeInMillis
            val sdf = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
            return sdf.format(date).toString()

        }

        fun loadImage(context: Context?, imageURL:String, imageView: ImageView, progressBar: ProgressBar){
            println("From PIcaSSo")
            Picasso
                .with(context)
                .load(imageURL)
                .placeholder(R.drawable.placeholder_picture_of_day)
                .into(imageView, object: Callback {
                    override fun onSuccess() {
                        imageView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }

                    override fun onError() {
                        Log.e("error","Image not loading")
                    }

                })
        }
    }


}

