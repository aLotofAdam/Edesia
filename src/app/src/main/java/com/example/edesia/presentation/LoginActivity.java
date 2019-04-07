package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signUp;
    EditText Username;
    EditText Password;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        myDb = new DatabaseHelper(this);
        Username = (EditText)findViewById(R.id.LoginUserName);
        Password = (EditText)findViewById(R.id.LoginPassword);
        login = (Button)findViewById(R.id.LoginButton);
        signUp = (Button)findViewById(R.id.SignUpButton);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUpActivity();
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = Username.getText().toString();
                String pass = Password.getText().toString();


                boolean checkLogin = myDb.checkLogin(user, pass);

                if (checkLogin == true) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    openUserMenuActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openUserMenuActivity(){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }
}
