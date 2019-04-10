package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class
RecipeOverview extends AppCompatActivity {

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DRAWABLE = "EXTRA_DRAWABLE";

    private TextView dateAndTime;
    private TextView message;
    private View coloredBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_recipe_overview);

        /* need to change to pass just a Unique id as an extra, and then retrieve the appropriate Data from a Service.*/
        /*Intent i = getIntent();
        String dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME);
        String messageExtra = i.getStringExtra(EXTRA_MESSAGE);
        int drawableResourceExtra = i.getIntExtra(EXTRA_DRAWABLE, 0);

        //dateAndTime = (TextView) findViewById(R.id.lbl_date_and_time_header);
        dateAndTime.setText(dateAndTimeExtra);

        message = (TextView) findViewById(R.id.lbl_message_body);
        message.setText(messageExtra);

        coloredBackground = findViewById(R.id.imv_colored_background);
        coloredBackground.setBackgroundResource(
                drawableResourceExtra
        );


*/
    }
}
