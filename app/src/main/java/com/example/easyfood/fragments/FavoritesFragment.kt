package com.example.easyfood.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.R
import com.example.easyfood.activities.MainActivity
import com.example.easyfood.adapters.FavoritesMealsAdapter
import com.example.easyfood.databinding.FragmentFavoritesBinding
import com.example.easyfood.viewModel.HomeViewModel
import com.example.easyfood.viewModel.MealViewModel


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    //Instance of the home view model - contains the favorite meals live data
    private lateinit var viewModel: HomeViewModel
    //Instance from the favorites adapter
    private lateinit var favoritesAdapter: FavoritesMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        observeFavorites()
    }

    private fun prepareRecyclerView() {
        favoritesAdapter = FavoritesMealsAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favoritesAdapter
        }
    }
    //To set the meals inside the adapter
    private fun observeFavorites() {
        viewModel.observeFavoriteMealsLiveData().observe(requireActivity(), Observer { meals->
            favoritesAdapter.differ.submitList(meals)
        })
    }

}