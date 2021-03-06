package com.example.edesia.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signUp;
    EditText Username;
    EditText Password;
    DatabaseHelper myDb;
    public String currentUser;

    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); //changed in merge
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
                    setCurrentUser(user);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    openHomeActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public LoginActivity() {
        getCurrentUser();
    }
    public void setCurrentUser(String user){
        currentUser = user;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void openSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}