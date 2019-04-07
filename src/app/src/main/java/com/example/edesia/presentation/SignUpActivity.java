package com.example.edesia.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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
     //   SignUpActivity.context = getApplicationContext();

        myDb = new DatabaseHelper(this);

        name = (EditText)findViewById(R.id.editTextName);
        username = (EditText)findViewById(R.id.editTextUsername);
        email = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);
        confirmPassword = (EditText)findViewById(R.id.editTextConfirmPassword);
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
                String UserName = username.getText().toString();
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Pass = password.getText().toString();
                String cPass = confirmPassword.getText().toString();

                    boolean fields = fieldsComplete(Name, UserName, Email, Pass, cPass);
                    boolean passwordsMatch = confirmPassword(Pass, cPass);
                    boolean userCheck = myDb.checkUsername(UserName);

                    if (fields && passwordsMatch && userCheck) {
                        boolean inserted = myDb.insertData(Name, UserName, Email, Pass);
                        if(inserted == true) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                            openLoginActivity();
                        }

                    } else if (!userCheck) {
                        Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    private boolean confirmPassword(String pass, String cPass){

        if(pass.equals(cPass)) {
            return true;
        }else{
            Toast.makeText(getApplicationContext(), "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean fieldsComplete(String name, String user, String email, String pass, String cPass){
        if(name.equals("")||user.equals("")||email.equals("")||pass.equals("")||cPass.equals("")){
            Toast.makeText(getApplicationContext(), "Fields Missing", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
