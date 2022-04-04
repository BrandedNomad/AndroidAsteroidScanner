package com.example.asteroidscanner.ui.detail

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.asteroidscanner.R
import com.example.asteroidscanner.databinding.FragmentDetailBinding
import com.example.asteroidscanner.databinding.FragmentMainBinding
import com.example.asteroidscanner.shared.Utils
import com.google.android.gms.ads.AdRequest

class DetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false

        )

        //val asteroid = DetailFragmentArgs.fromBundle(this.arguments).selectedAsteroid

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid

        val application = requireNotNull(activity).application
        val viewModelFactory = DetailViewModelFactory(asteroid,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        viewModel.selectedAsteroid.observe(viewLifecycleOwner, Observer{
            Utils.bindAsteroidDetails(binding,it)
        })

        val showInfo = binding.helpButton
        showInfo.setOnClickListener{
            var infoDialog = AlertDialog.Builder(this.context)
            var msg = "The astronomical unit (au) is a unit of length, roughly the distance from Earth to the Sun and equal to about 150 million kilometers (93 million miles)"

            infoDialog
                .setMessage(msg)
                .setIcon(R.drawable.ic_help_circle)
                .setCancelable(false)
                .setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener{dialogInterface, which  ->
                        dialogInterface.cancel()
                    }
                )

            infoDialog.create().show()
        }

        var mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        return binding.root

    }


}