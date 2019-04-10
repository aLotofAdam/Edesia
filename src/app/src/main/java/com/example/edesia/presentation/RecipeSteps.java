package com.example.edesia.presentation;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class
RecipeSteps extends AppCompatActivity {
    //string to get from the database
    private static final String recipeName = "Title";
    private static final String prepTime = "PrepTime";
    private static final String totalTime = "totalTime";
    private static final String ingrediates = "Ingrediates";
    private static final String instructions = "Instructions";
    //UI
    private TextView recipenameLabel;
    private TextView message;
    private View coloredBackground;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.content_recipe_steps);
        Intent i = getIntent();

        String title = i.getStringExtra(recipeName);
        String ptime = i.getStringExtra(prepTime);
        String ttime = i.getStringExtra(totalTime);
        String ingredients_list = i.getStringExtra(ingrediates);
        String instructions_list = i.getStringExtra(instructions);

        recipenameLabel = (TextView)findViewById(R.id.lbl_recipe_Title);


    }


}
