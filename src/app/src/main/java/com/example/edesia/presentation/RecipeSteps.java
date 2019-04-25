package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeSteps extends AppCompatActivity {
    String intentTitle = "title";
    String intentIngredients = "ingredients";
    String intentInstructions = "instructions";
    String intentID = "id";
    String intentUrl = "url";
    DatabaseHelper myDb;
    LoginActivity currentUser;
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
        String url = i.getStringExtra(intentUrl);

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
        image = findViewById(R.id.lbl_image);
        Glide.with(this).load(url).into(image);
        final String Id= id;


        FloatingActionButton add = (FloatingActionButton)this.findViewById(R.id.fab_create_new_item);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //change class to johns class
                Intent intent = new Intent(getApplicationContext(), Plan.class);
                intent.putExtra("id", Id);
                startActivity(intent);

            }
        });


        FloatingActionButton favorite = (FloatingActionButton)this.findViewById(R.id.fab_create_new_item2);


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 myDb.insertFavoriteRecipe(currentUser.getCurrentUser(), Integer.parseInt(intentID));
            }
        });
    }
}
