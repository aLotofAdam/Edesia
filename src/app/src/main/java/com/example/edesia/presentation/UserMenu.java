package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class UserMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_menu);

        Button favs = (Button)findViewById(R.id.userFavoritesButton);
        Button planned = (Button)findViewById(R.id.userPlannedButton);
        Button settings = (Button)findViewById(R.id.userSettingsButton);
        Button back = (Button)findViewById(R.id.userMenuBackButton);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });

    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
