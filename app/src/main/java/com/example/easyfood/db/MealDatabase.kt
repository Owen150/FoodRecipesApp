package com.example.easyfood.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easyfood.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase(){
    //Instance from the MealDao Interface Class to make its functions available
    abstract fun mealDao():MealDao
    //Function to return an instance from the database class
    companion object{
        //Any change on this instance from in-thread will be visible to any other thread
        @Volatile
        var INSTANCE:MealDatabase? = null

        //Only one thread can have an instance from this room database
        @Synchronized
        fun getInstance(context:Context):MealDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }
}