package com.example.asteroidscanner.shared


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.asteroidscanner.R
import com.example.asteroidscanner.data.network.responseclasses.GetImageResponse
import com.example.asteroidscanner.databinding.FragmentDetailBinding
import com.example.asteroidscanner.databinding.FragmentMainBinding
import com.example.asteroidscanner.domain.Asteroid
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

        fun loadImage(context: Context?, binding: FragmentMainBinding, image: GetImageResponse){
            println("From PIcaSSo")
            Picasso
                .with(context)
                .load(image.url)
                .placeholder(R.drawable.placeholder_picture_of_day)
                .into(binding.imageOfTheDay, object: Callback {
                    override fun onSuccess() {
                        binding.imageOfTheDay.visibility = View.VISIBLE
                        binding.imageOfTheDay.contentDescription = image.title
                        binding.progressBar.visibility = View.GONE
                    }

                    override fun onError() {
                        Log.e("error","Image not loading")
                    }

                })
        }

        fun bindAsteroidDetails(binding: FragmentDetailBinding, asteroid: Asteroid){
            if(asteroid.status){
                binding.statusImage.setImageResource(R.drawable.asteroid_hazardous)
            }else{
                binding.statusImage.setImageResource(R.drawable.asteroid_safe)
            }

            val absoluteMagnitudeString = asteroid.absolute_magnitude.toString() + " au"
            val estimatedDiameterString = asteroid.estimated_diameter.toString() + " km"
            val distanceFromEarthString = asteroid.distance_from_earth.toString() + " au"
            val relativeVelocityString = asteroid.relative_velocity.toString() + " km/s"


            binding.statusImage.visibility = View.VISIBLE
            binding.statusProgressBar.visibility = View.GONE
            binding.infoAbsoluteMagnitude.text = absoluteMagnitudeString
            binding.infoCloseApproach.text = asteroid.close_approach_date.toString()
            binding.infoDistanceFromEarth.text = distanceFromEarthString
            binding.infoEstimatedDiameter.text = estimatedDiameterString
            binding.infoRelativeVelocity.text = relativeVelocityString

        }
    }


}
