package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class
RecipeOverview extends AppCompatActivity {

    private static final String Title = "Title";
    private static final String  PrepTime= "PrepTime";
    private static final String TotalTime = "TotalTime";
    private static final String Ingrediates = "Ingrediates";
    private static final String Instructions = "Instructions";
    private static final String Picture= "Picture";
    private TextView lbl_title;
    private TextView lbl_prepTime;
    private TextView lbl_TotalTime;
    private TextView lbl_instructionse;
    private TextView lbl_ingrediates;
    private ImageView Picture_image;
    private View coloredBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        //setContentView(R.layout.content_recipe_overview);

        Intent i = getIntent();
        String Title = i.getStringExtra(Title);
        String
        String messageExtra = i.getStringExtra();
        int drawableResourceExtra = i.getIntExtra(EXTRA_DRAWABLE, 0);

        lbl_prepTime = (TextView) findViewById(R.id.lbl_prepTime);
        lbl_prepTime.setText(dateAndTimeExtra);
        lbl_TotalTime = (TextView) findViewById(R.id.lbl_totalTime);
        lbl_TotalTime.setText(dateAndTimeExtra);

        lbl_instructionse = (TextView) findViewById(R.id.lbl_message_body);
        lbl_instructionse.setText(messageExtra);


        coloredBackground = findViewById(R.id.imv_colored_background);
        coloredBackground.setBackgroundResource(
                drawableResourceExtra
        );


    }
*/
    }
}
