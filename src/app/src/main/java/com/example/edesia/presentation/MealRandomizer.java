package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;



public class MealRandomizer extends AppCompatActivity {
    Random BreakfastRand = new Random();
    Random LunchRand = new Random();
    Random DinnerRand = new Random();

    int BreakfastID = BreakfastRand.nextInt(82) + 1;
    int LunchID = LunchRand.nextInt(82) + 1;
    int DinnerID = DinnerRand.nextInt(82) + 1;

    String selectedMonth;
    int selectedDay;

    DatabaseHelper myDbRandom = new DatabaseHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_randomizer);




        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        Spinner s4 = findViewById(R.id.month_spinner2);
        Spinner s5 = findViewById(R.id.day_spinner2);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s5.setAdapter(days);

      Button Randomize =  findViewById(R.id.randomize1);
        Randomize.setOnClickListener(RandomListener);


        selectedMonth = s4.getSelectedItem().toString();
        selectedDay = Integer.parseInt(s5.getSelectedItem().toString());



    }
    private View.OnClickListener RandomListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean inserted = myDbRandom.insertRandom("Steve1", selectedMonth, selectedDay, BreakfastID, LunchID, DinnerID);

            if(inserted == true) {
                Toast.makeText(getApplicationContext(), "Planned Successfully", Toast.LENGTH_LONG).show();
            }
        }
    };


}