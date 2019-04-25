package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class GroceryList extends AppCompatActivity {
    private EditText mItemEdit;
    private ArrayAdapter<String> mAdapter;
    String selectedMonth;
    int selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        final ListView mShoppingList = findViewById(R.id.listView);
        mItemEdit = findViewById(R.id.editText);
        Button mAddButton = findViewById(R.id.addButton);
        Button getList = findViewById(R.id.getListButton);

        // Construct an Intent object for groceryList
        final Intent intent = new Intent(this, OCR_Vision.class);
        final ArrayList<String> gList = new ArrayList<>();
        intent.putStringArrayListExtra("gList",gList);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        mShoppingList.setAdapter(mAdapter);

        //adds item to grocery list
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                mItemEdit.setText("");
            }
        });

        //deletes item from grocery list
        mShoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = mAdapter.getItem(position);
                mAdapter.remove(mAdapter.getItem(position));
                mAdapter.notifyDataSetChanged();
                Toast.makeText(GroceryList.this, "Got: " + item, Toast.LENGTH_SHORT).show();
            }
        });


        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add date from spinners, look at johns randmizer
            }
        });

        ImageButton googleVisionButton = findViewById(R.id.google_vision);
        googleVisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Camera Started", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //final ArrayList<String> gList = getList();

                intent.putStringArrayListExtra("gList",gList);
                // Start the groceryList
                startActivity(intent);
            }
        });

        ImageButton home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        // Output the array, not sure if need this
        for(String item:gList){
            Log.i("GroceryList", String.valueOf(item));
        }

        ArrayAdapter<CharSequence> months;
        ArrayAdapter<CharSequence> days;
        String[] monthArr = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        String[] dayArr = {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        Spinner s6 = findViewById(R.id.month_spinner3);
        Spinner s7 = findViewById(R.id.day_spinner3);

        months = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                monthArr);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s6.setAdapter(months);



        days = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,
                dayArr);
        days.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s7.setAdapter(days);


        selectedMonth = s6.getSelectedItem().toString();
        selectedDay = Integer.parseInt(s7.getSelectedItem().toString());

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}