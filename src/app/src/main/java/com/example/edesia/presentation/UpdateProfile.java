package com.example.edesia.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProfile extends AppCompatActivity {
    DatabaseHelper myDb;
    static Context context;

    EditText name;
    EditText username;

    EditText email;
    EditText password;
    EditText confirmPassword;
    Button backButton;
    Button updateProfile;

    String getUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        //   SignUpActivity.context = getApplicationContext();

        myDb = new DatabaseHelper(this);

        name = (EditText)findViewById(R.id.updateTextName);
        username = (EditText)findViewById(R.id.updateTextUsername);
        email = (EditText)findViewById(R.id.updateTextEmail);
        password = (EditText)findViewById(R.id.updateTextPassword);
        confirmPassword = (EditText)findViewById(R.id.updateTextConfirmPassword);
        backButton = findViewById(R.id.updateBackButton);
        updateProfile = findViewById(R.id.updateProfileButton);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUserMenuActivity();
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
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
                    boolean inserted = myDb.insertUserData(Name, UserName, Email, Pass);
                    if(inserted == true) {
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        openHomeActivity();
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

    public void openHomeActivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openUserMenuActivity(){
        Intent intent = new Intent(this, UserMenu.class);
        startActivity(intent);
    }
}
