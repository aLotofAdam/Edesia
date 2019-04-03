package com.example.edesia.presentation;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText textInputName;
    private TextInputEditText textInputUsername;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputPassword;
    private TextInputEditText textInputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    //Listener for navigation component
    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    private boolean confirmPassword(){

        if(textInputPassword.equals(textInputConfirmPassword)) {
            return true;
        }else{
            textInputPassword.setError("Passwords Do Not Match");
            return false;
        }
    }
}
