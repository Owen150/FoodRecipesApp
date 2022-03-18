package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.MealItemBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealsByCategory

class FavoritesMealsAdapter : RecyclerView.Adapter<FavoritesMealsAdapter.FavoritesMealsAdapterViewHolder>() {

    lateinit var onItemClick:((MealsByCategory)->Unit)

    private var mealsList = ArrayList<MealsByCategory>()

    inner class FavoritesMealsAdapterViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)
    //DiffUtil Gives the recycler view more performance as it acts on an individual item and not the entire list i.e Deletes and Updates one but not all
    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        //Will compare the primary key between the old item and the new item i.e Will return true iff the ids of the 2 meals are the same
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }
        //Will compare the whole object i.e the meals, and return true iff the two meals are the same
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
    //We will use this value to get an item from the list
    val differ = AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesMealsAdapterViewHolder {
        return FavoritesMealsAdapterViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesMealsAdapterViewHolder, position: Int) {
        //To set the information of the meal in our layout, use differ value
        val meal = differ.currentList[position]
        Glide.with(holder.itemView)
            .load(meal.strMealThumb)
            .into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = meal.strMeal

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}