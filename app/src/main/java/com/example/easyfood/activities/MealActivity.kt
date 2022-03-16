package com.example.easyfood.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.MealViewModel
import com.example.easyfood.viewModel.MealViewModelFactory

class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding: ActivityMealBinding
    private lateinit var youtubeLink:String
    private lateinit var mealMvvm:MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Getting an instance from the meal database and the viewmodel factory. Get instance returns an instance from the room db
        val mealDatabase = MealDatabase.getInstance(this)
        //MealViewModelFactory initializes the view model in this activity
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        mealMvvm = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInViews()

        loadingCase()

        mealMvvm.getMealDetail(mealId)

        observerMealDeatailsLiveData()

        onYoutubeImageClick()

        onFavoriteClick()
    }
    //Function to insert our favorite meal to our database, toast message to the user
    private fun onFavoriteClick() {
        binding.addToFavorites.setOnClickListener {
            mealToSave?.let {
                mealMvvm.insertMeal(it)
                Toast.makeText(this, "Meal Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //Function that sends you to the youtube channel when clicked
    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }
    //Makes sure the meal to save is != null
    //Assigns it the same value as the meal response queried from the MealAPI
    private var mealToSave: Meal?=null
    private fun observerMealDeatailsLiveData() {
        mealMvvm.observerMealDeatailsLiveData().observe(this
        ) { t ->
            mealToSave = t
            onResponseCase()
            binding.tvCategory.text = "Category : ${t!!.strCategory}"
            binding.tvArea.text = "Area : ${t.strArea}"
            binding.tvInstructionsSteps.text = t.strInstructions
            youtubeLink = t.strYoutube.toString()
        }
    }
    //Loading the images into the views using glide
    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }
    //Getting the specific meal info
    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
    //Set the loading screen while the app fetches data from the API
    private fun loadingCase(){
        //Make the progress bar visible
        binding.progressBar.visibility = View.VISIBLE
        //Set the invisible components
        binding.addToFavorites.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    //The screen after we get a response from the API
    private fun onResponseCase(){
        //Make the progress bar invisible
        binding.progressBar.visibility = View.INVISIBLE
        //Set the visible components
        binding.addToFavorites.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}