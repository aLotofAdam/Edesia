package com.example.edesia;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.edesia.Database.Database;

import com.bumptech.glide.Glide;
import com.example.edesia.Database.Database;
import com.example.edesia.Model.RecipeModel;
import com.example.hp.edesia.R;

import java.util.List;

public class RecipeSteps extends AppCompatActivity {
    String intentTitle = "title";
    String intentIngredients = "ingredients";
    String intentInstructions = "instructions";
    String intentID = "id";
    TextView ingredients, instructions, title;
    //FloatingActionButton add;
    //Button favorite;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe_steps);
        //get information from intent sent from recipe page
        Intent i = getIntent();
        String Title = i.getStringExtra(intentTitle);
        String Instructions = i.getStringExtra(intentInstructions);
        String Ingredients = i.getStringExtra(intentIngredients);
        String id = i.getStringExtra(intentID);
        final RecipeModel recipe;
        Database database;
        database = new Database(this);
        database.getRecipeModel();
        //init view and query for results
        title = findViewById(R.id.lbl_Title);
        title.setText(Title);
        ingredients = findViewById(R.id.lbl_Ingredients);
        ingredients.setText(Ingredients);
        instructions = findViewById(R.id.lbl_Instructions);
        instructions.setText(Instructions);
        //image = findViewById(R.id.lbl_image);

        FloatingActionButton add = (FloatingActionButton)this.findViewById(R.id.fab_create_new_item);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to johns class
                String id_pass = intentID;
                //change class to johns class
                Intent intent = new Intent(RecipeSteps.this, RecipeSearch.class);
                intent.putExtra("id", id_pass);
                startActivity(intent);

            }
        });


        FloatingActionButton favorite = (FloatingActionButton)this.findViewById(R.id.fab_create_new_item2);


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change to send to stephens database
                String id_pass = intentID;
                //change class to johns class
                Intent intent = new Intent(RecipeSteps.this, RecipeSearch.class);
                intent.putExtra("id", id_pass);
                startActivity(intent);

            }
        });


    }
}
