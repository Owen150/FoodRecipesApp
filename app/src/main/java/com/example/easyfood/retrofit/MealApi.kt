package com.example.easyfood.retrofit

import com.example.easyfood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    //To get data from the API
    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}