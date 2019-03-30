package com.example.edesia.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    static Context context;

    EditText name;
    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button backButton;
    Button registerButton;
    boolean regComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up);
        SignUpActivity.context = getApplicationContext();

        name = findViewById(R.id.editTextName);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        backButton = findViewById(R.id.signUpBackButton);
        registerButton = findViewById(R.id.RegisterButton);



        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do{
                        try {
                            boolean fields = fieldsComplete(name.toString(), username.toString(), email.toString(), password.toString(), confirmPassword.toString());
                            boolean passwordsMatch = confirmPassword(password.toString(), confirmPassword.toString());
                            boolean inserted = myDb.insertData(name.toString(), username.toString(), email.toString(), password.toString());
                            if (fields && passwordsMatch && inserted) {
                                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                regComplete = true;

                            } else {
                                Toast.makeText(context, "Registration Incomplete", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                } while(!regComplete);

                openLoginActivity();
            }
        });
    }

    private boolean confirmPassword(String pass, String cPass){

        if(password.toString().equals(confirmPassword.toString())) {
            return true;
        }else{
            Toast.makeText(context, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean fieldsComplete(String name, String user, String email, String pass, String cPass){
        if(name.equals("")||username.equals("")||email.equals("")||password.equals("")||confirmPassword.equals("")){
            Toast.makeText(context, "Fields missing", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
