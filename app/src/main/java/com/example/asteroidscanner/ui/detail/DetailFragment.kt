package com.example.asteroidscanner.ui.detail

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

        return binding.root

    }


}