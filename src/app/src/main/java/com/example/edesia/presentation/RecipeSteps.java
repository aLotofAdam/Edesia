package com.example.edesia;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    String intentTitle= "title";
    TextView ingredients,instructions,title ;

    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe_steps);
        //get title from the search activity
        Intent i = getIntent();
        String Title = i.getStringExtra(intentTitle);
        String Instructions;
        String Ingredients;
        RecipeModel recipe;
        Database database;
        database = new Database(this);
        database.getRecipeModel();
        //init view and query for results
        title = findViewById(R.id.lbl_Title);
        title.setText(Title);
        //ingredients= findViewById(R.id.lbl_Ingredients);
        //ingredients.setText(database.getIngredients());
        //instructions = findViewById(R.id.lbl_Ingredients);
       // image = findViewById(R.id.lbl_image);


    }

  /*  private void setImage(String imageURL, String imageName){
        // useglide library to set image
        TextView name = findViewById(R.id.lbl_image);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }*/
}
