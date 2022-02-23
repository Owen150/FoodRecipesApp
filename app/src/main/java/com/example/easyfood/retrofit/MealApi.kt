package com.example.easyfood.retrofit

import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    //To get data from the API
    @GET("random.php")
    fun getRandomMeal():Call<MealList>

    //To get meal details by id from the API
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>

    //To get popular items from the api
    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName:String) : Call<CategoryList>
}