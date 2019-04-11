package com.example.edesia.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;



public class MealRandomizer extends AppCompatActivity {
    Random rand = new Random();

    int number = rand.nextInt(300) + 1;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_meal_randomizer);




        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};

        Spinner s3 = (Spinner)findViewById(R.id.month_spinner2);
        Spinner s4 = (Spinner)findViewById(R.id.day_spinner2);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(days);
    }


}

