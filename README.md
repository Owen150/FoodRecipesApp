# FoodRecipesApp
Food Recipes App in Kotlin using MVVM Architecture.

Libraries and technologies used to build this app:
1. Navigation component - One activity contains multiple fragments instead of creating multiple activites
2. Retrofit - Making HTTP connection with the rest API and converting the meal json file to a Kotlin object
3. Room - Saving meals in a local database
4. MVVM & LiveData - Saperating the logic code from views and saving the application state in case the screen configuration changes
5. Coroutines - Doing some code in the background
6. view binding - Instead of inflating views manually, view binding will take care of that
7. Glide - Catching images and loading them in an imageView
