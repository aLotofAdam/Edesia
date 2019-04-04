package com.example.edesia.presentation;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class Home extends AppCompatActivity {

    Spinner s1;
    Spinner s2;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);


        s1.findViewById(R.id.month_spinner1);
        s2.findViewById(R.id.day_spinner1);

    ArrayAdapter<CharSequence> months = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
    ArrayAdapter<CharSequence> numbers = ArrayAdapter.createFromResource(this, R.array.Numbers, android.R.layout.simple_spinner_item);
    months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    numbers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    s1.setAdapter(months);
    s2.setAdapter(numbers);




}
}
