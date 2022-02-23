package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.pojo.MealsByCategory

class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    //Implementing onclick listener for the popular meals items
    lateinit var onItemClick:((MealsByCategory)->Unit)
    //Implementing the category meals-meals list
    private var mealsList = ArrayList<MealsByCategory>()
    //Public method for setting a meal list from outside of this class
    fun setMeals(mealsByCategoryList:ArrayList<MealsByCategory>){
        this.mealsList = mealsByCategoryList
        notifyDataSetChanged()  //As every time we set a meal list we want to refresh the adapter to obtain the views
    }
    //Recycler View Functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    //Load the popular meal image
    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealsList[position])
        }
    }
    //Return the size of the meal list
    override fun getItemCount(): Int {
        return mealsList.size
    }

    class PopularMealViewHolder(var binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root)
}