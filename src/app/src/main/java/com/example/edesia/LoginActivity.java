package com.example.edesia;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText textInputUsername;
    private TextInputEditText textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
