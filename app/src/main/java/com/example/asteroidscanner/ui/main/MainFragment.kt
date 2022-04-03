package com.example.asteroidscanner.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidscanner.R
import com.example.asteroidscanner.databinding.FragmentMainBinding
import com.example.asteroidscanner.domain.Asteroid
import com.example.asteroidscanner.shared.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.net.URI

class MainFragment:Fragment() {

    lateinit var activityReference: MainFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activityReference = this

        //Bind and inflate the fragment
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        //Set as lifecycleOwner
        binding.lifecycleOwner = this

        val activity = requireNotNull(this.activity)

        //Get the ViewModel
        var viewModelFactory = MainViewModelFactory(activity.application)
        var viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        //setup adapter
        var adapter = MainAdapter(MainAdapter.OnClickListener{
            viewModel.displayAsteroidDetails(it)
        })
        var recyclerView: RecyclerView = binding.mainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter







        //OBSERVERS

        //Populates the initial list
        viewModel.asteroidList.observe(viewLifecycleOwner, Observer{
            //SetAdapter
            it?.let{
                adapter.submitList(it)
            }

        })

        //Loads the Image of the Day
        viewModel.dailyImage.observe(viewLifecycleOwner,Observer{
            Log.e("MainFragment","The dailyImage has changed")

            Utils.loadImage(
                this.context,
                binding,
                it)



        })

        //Navigates to detail screen when item is selected
        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner,Observer{
            if(null !=it){
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentDestinationToDetailFragmentDestination(it))
                viewModel.displayAsteroidDetailsComplete()
            }
        })


        return binding.root
    }
}