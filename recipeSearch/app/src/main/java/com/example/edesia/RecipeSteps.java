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

import com.bumptech.glide.Glide;
import com.example.edesia.Model.RecipeModel;
import com.example.hp.edesia.R;

import java.util.List;

public class RecipeSteps extends AppCompatActivity {
    TextView title, ingredients, instructions;
    ImageView image;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe_steps);

        title = findViewById(R.id.lbl_Title);
        ingredients= findViewById(R.id.lbl_Ingredients);
        instructions = findViewById(R.id.lbl_Ingredients);
        image = findViewById(R.id.lbl_image);


        getIncomingIntent();
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("title")){
            /*pull recipe based on the title name passed from the database
             */
            String title = getIntent().getStringExtra("title");

        }
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
