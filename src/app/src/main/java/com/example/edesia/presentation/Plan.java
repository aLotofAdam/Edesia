package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Plan extends AppCompatActivity {
    String selectedMonth;
    int selectedDay;
    String selectedMeal;
    int recipeIDHold;
    Button Add;

    DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_plan);

        Button home = findViewById(R.id.HomeButton);
        Button search = findViewById(R.id.SearchButton);


        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        ArrayAdapter<CharSequence> choice;

        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        String[] mealChoice = {"Breakfast", "Lunch", "Dinner"};

        Spinner s1 = findViewById(R.id.month_spinner1);
        Spinner s2 = findViewById(R.id.day_spinner1);
        Spinner s3 = findViewById(R.id.meal_choice1);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(days);


        choice = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                mealChoice);
        choice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(choice);

        Add = findViewById(R.id.Add);
        Add.setOnClickListener(AddListener);

        selectedMonth = s1.getSelectedItem().toString();
        selectedDay = Integer.parseInt(s2.getSelectedItem().toString());
        selectedMeal = s3.getSelectedItem().toString();


        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                backToHome();
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRecipeSearch();
            }
        });


    }

    private View.OnClickListener AddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean inserted = myDb.insertPlannedMeal("Steve1", selectedMonth, selectedDay, selectedMeal, recipeIDHold);

            if(inserted == true) {
                Toast.makeText(getApplicationContext(), "Planned Successfully", Toast.LENGTH_LONG).show();
            }
        }
    };

    public void backToHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openRecipeSearch(){
        Intent intent = new Intent(this, RecipeSearch.class);
        startActivity(intent);
    }

}

