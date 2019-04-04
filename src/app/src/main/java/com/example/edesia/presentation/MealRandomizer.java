package com.example.edesia.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



public class MealRandomizer extends AppCompatActivity {
/*


    Spinner s3 = findViewById(R.id.month_spinner2);
    Spinner s4 = findViewById(R.id.day_spinner2);
    Button b8 = findViewById(R.id.back_to_home2);
    Button b9 = findViewById(R.id.randomize1);

*/
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_meal_randomizer);
/*

        ArrayAdapter<CharSequence> months = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> numbers = ArrayAdapter.createFromResource(this, R.array.Numbers, android.R.layout.simple_spinner_item);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numbers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s3.setAdapter(months);
        s4.setAdapter(numbers);

        b8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
  */
    }

/*

    public void openHomeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

*/
}

