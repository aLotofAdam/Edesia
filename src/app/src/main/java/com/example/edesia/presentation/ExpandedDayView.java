package com.example.edesia.presentation;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class ExpandedDayView extends AppCompatActivity{
    /*
    Button b4 = findViewById(R.id.meal_randomizer2);
    Button b5 = findViewById(R.id.search2);
    Button b6 = findViewById(R.id.grocery_list2);
    Button b7 = findViewById(R.id.back_to_home);
*/

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_expanded_day_view);
/*
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMealRandomizer();
            }
        });

        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRecipeSearch();
            }
        });

        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGroceryList();
            }
        });

        b7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
*/
    }
/*
    public void openMealRandomizer(){
        Intent intent = new Intent(this, MealRandomizer.class);
        startActivity(intent);
    }

    public void openRecipeSearch(){
        Intent intent = new Intent(this, RecipeSearch.class);
        startActivity(intent);
    }

    public void openGroceryList(){
        Intent intent = new Intent(this, GroceryList.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
*/
}

